package org.example.lesson9;

import org.example.lesson9.dao.AddressDAO;
import org.example.lesson9.dao.PersonDAO;
import org.example.lesson9.dao.impl.AddressDAOImpl;
import org.example.lesson9.dao.impl.PersonDAOImpl;
import org.example.lesson9.dto.Address;
import org.example.lesson9.dto.Person;
import org.example.lesson9.utils.JsonManager;

import java.io.IOException;
import java.util.List;

public class DemoApp {
    private static final String PEOPLE_IN_FILE_PATH = "src\\main\\resources\\persons.json";
    private static final String ADDRESSES_IN_FILE_PATH = "src\\main\\resources\\addresses.json";
    private static final PersonDAO PERSON_DAO = new PersonDAOImpl();
    private static final AddressDAO ADDRESS_DAO = new AddressDAOImpl();


    public static void main(String[] args) {
        try {
            List<Person> people = JsonManager.readDTOList(PEOPLE_IN_FILE_PATH, Person.class);
            List<Address> addresses = JsonManager.readDTOList(ADDRESSES_IN_FILE_PATH, Address.class);
            people.forEach(PERSON_DAO::save);
            addresses.forEach(ADDRESS_DAO::save);

            people.stream()
                    .filter(p -> people.indexOf(p) == people.size() - 1
                            || people.indexOf(p) == people.size() - 2)
                    .forEach(p -> PERSON_DAO.increaseAge(p.getId(), 2));

            addresses.stream()
                    .filter(h -> addresses.indexOf(h) == addresses.size() - 1
                            || addresses.indexOf(h) == addresses.size() - 2)
                    .forEach(h -> ADDRESS_DAO.increaseHouseNumber(h.getId(), 1));

            PERSON_DAO.delete(people.get(0).getId(), Person.class);
            ADDRESS_DAO.delete(addresses.get(0).getId(), Address.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
