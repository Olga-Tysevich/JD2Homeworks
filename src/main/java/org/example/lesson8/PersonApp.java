package org.example.lesson8;

import org.example.lesson8.dao.PersonDAO;
import org.example.lesson8.dao.impl.PersonDAOImpl;
import org.example.lesson8.dto.Person;
import org.example.lesson8.util.JsonManager;

import java.util.List;

public class PersonApp {
    private static final String IN_FILE_PATH = "src\\main\\resources\\persons.json";
    private static final String OUT_FILE_PATH = "src\\main\\resources\\personsWithId.json";

    private static final PersonDAO personDAO = new PersonDAOImpl();

    public static void main(String[] args) {
        List<Person> personList = JsonManager.readPersons(IN_FILE_PATH);

        if (personList != null) {
            personList.forEach(System.out::println);

            personDAO.saveAll(personList);

            personList.forEach(System.out::println);

            personList = personDAO.getPersonsOverAge(21);

            System.out.println("Person over 21 age by date_time_create:");
            personList.forEach(System.out::println);

            JsonManager.writePersons(personList, OUT_FILE_PATH);

        }
    }
}
