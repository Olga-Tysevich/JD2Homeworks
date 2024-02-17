package org.example.lesson9;

import org.example.lesson9.dao.AddressDAO;
import org.example.lesson9.dao.PersonDAO;
import org.example.lesson9.dao.impl.AddressDAOImpl;
import org.example.lesson9.dao.impl.PersonDAOImpl;
import org.example.lesson9.dto.AddressDTO;
import org.example.lesson9.dto.PersonDTO;
import org.example.lesson9.utils.HibernateUtil;
import org.example.lesson9.utils.JsonManager;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import static org.example.lesson9.utils.Constants.ADDRESSES_IN_FILE_PATH;
import static org.example.lesson9.utils.Constants.PEOPLE_IN_FILE_PATH;

public class DemoApp {
    private static final PersonDAO PERSON_DAO = new PersonDAOImpl();
    private static final AddressDAO ADDRESS_DAO = new AddressDAOImpl();

    public static void main(String[] args) {
        try {
            List<PersonDTO> people = JsonManager.readDTOList(PEOPLE_IN_FILE_PATH, PersonDTO.class);
            List<AddressDTO> addressDTOS = JsonManager.readDTOList(ADDRESSES_IN_FILE_PATH, AddressDTO.class);

            IntStream.range(0, people.size())
                    .forEach(i -> {
                        people.get(i).setAddress(addressDTOS.get(i));
                        PERSON_DAO.save(people.get(i));
                    });

            people.stream()
                    .filter(p -> people.indexOf(p) == people.size() - 1
                            || people.indexOf(p) == people.size() - 2)
                    .forEach(p -> PERSON_DAO.increaseAge(p.getId(), 2));

            addressDTOS.stream()
                    .filter(h -> addressDTOS.indexOf(h) == addressDTOS.size() - 1
                            || addressDTOS.indexOf(h) == addressDTOS.size() - 2)
                    .forEach(h -> ADDRESS_DAO.increaseHouseNumber(h.getId(), 1));

            PersonDTO p = PERSON_DAO.get(people.get(0).getId());

            AddressDTO addressDTO = p.getAddress();
            System.out.println(addressDTO);

            PersonDTO p2 = people.get(1);
            p2.setAddress(addressDTOS.get(0));
            PERSON_DAO.save(p2);
            PERSON_DAO.update(p2);

            PERSON_DAO.delete(p.getId());
            PERSON_DAO.delete(p2.getId());

            PERSON_DAO.closeSession();
            ADDRESS_DAO.closeSession();
            HibernateUtil.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
