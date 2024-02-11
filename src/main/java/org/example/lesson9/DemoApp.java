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

import static org.example.lesson9.utils.Constants.ADDRESSES_IN_FILE_PATH;
import static org.example.lesson9.utils.Constants.PEOPLE_IN_FILE_PATH;

public class DemoApp {
    private static final PersonDAO PERSON_DAO = new PersonDAOImpl();
    private static final AddressDAO ADDRESS_DAO = new AddressDAOImpl();

    public static void main(String[] args) {
        try {
            List<PersonDTO> people = JsonManager.readDTOList(PEOPLE_IN_FILE_PATH, PersonDTO.class);
            List<AddressDTO> addressDTOS = JsonManager.readDTOList(ADDRESSES_IN_FILE_PATH, AddressDTO.class);
            people.forEach(PERSON_DAO::save);
            addressDTOS.forEach(ADDRESS_DAO::save);

            people.stream()
                    .filter(p -> people.indexOf(p) == people.size() - 1
                            || people.indexOf(p) == people.size() - 2)
                    .forEach(p -> PERSON_DAO.increaseAge(p.getId(), 2));

            addressDTOS.stream()
                    .filter(h -> addressDTOS.indexOf(h) == addressDTOS.size() - 1
                            || addressDTOS.indexOf(h) == addressDTOS.size() - 2)
                    .forEach(h -> ADDRESS_DAO.increaseHouseNumber(h.getId(), 1));

            PERSON_DAO.delete(people.get(0).getId());
            ADDRESS_DAO.delete(addressDTOS.get(0).getId());

            HibernateUtil.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
