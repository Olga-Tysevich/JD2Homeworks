package org.example.lesson7;


import org.example.lesson7.connection.SQLConnection;
import org.example.lesson7.dao.PersonDAO;
import org.example.lesson7.dao.PersonDAOImpl;
import org.example.lesson7.dto.PersonDTO;
import org.example.lesson7.utils.GsonManager;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonApp {
    private static final String PERSONS_IN_FILE_PATH = "src\\main\\resources\\persons.json";
    private static final String PERSONS_OUT_FILE_PATH = "src\\main\\resources\\persons2.json";
    private static final GsonManager gsonManager = new GsonManager();
    private static final PersonDAO personDAO = new PersonDAOImpl();
    private static final Random RANDOM = new Random();

    private static final String CREATE_DATABASE_QUERY = "create database people";
    private static final String CREATE_TABLE_QUERY = "create table people.person "
            + "(id INT PRIMARY KEY AUTO_INCREMENT,"
            + "age INT, salary DECIMAL, "
            + "passport CHAR(10), "
            + "address VARCHAR(200), "
            + "date_of_birthday DATE, "
            + "date_time_create TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
            + "time_to_lunch TIME, "
            + "letter TEXT)";
    private static final String DROP_DATABASE_QUERY = "drop database people";

    public static void main(String[] args) throws SQLException, IOException {
        createDatabase();

        List<PersonDTO> personDTOList = gsonManager.readPersonDTOList(PERSONS_IN_FILE_PATH);
        System.out.println("PersonList before save:");
        personDTOList.forEach(System.out::println);

        List<PersonDTO> personDTOAfterSave = new ArrayList<>();
        personDTOList.forEach(p -> {
            try {
                personDTOAfterSave.add(personDAO.save(p));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        System.out.println("\nPersonList after save: \n");
        personDTOAfterSave.forEach(System.out::println);

        int randomId = RANDOM.nextInt(personDTOAfterSave.size() + 1);
        PersonDTO personDTO1 = personDAO.get(randomId);

        System.out.println("\nGet random id: " + randomId + " -> " + personDTO1 + "\n");
        personDTO1.setAddress("another address");
        System.out.println("Updated row: " + personDAO.update(personDTO1));
        System.out.println("\nPerson after update: " + personDAO.get(personDTO1.getId()) + "\n");


        System.out.println("Person list before delete:");
        personDAO.getAll().forEach(System.out::println);
        System.out.println("Deleted row: " + personDAO.delete(personDTO1));
        System.out.println("\nPerson list after delete:");
        List<PersonDTO> personDTOS = personDAO.getAll();
        personDTOS.forEach(System.out::println);

        gsonManager.writePersonDTOList(PERSONS_OUT_FILE_PATH, personDTOS);

        dropDatabase(); //Это просто для меня, что бы одно задание не мешало тренироваться в другом =)
        SQLConnection.closeConnection();
    }

    private static void createDatabase() {
        try (Statement statement = SQLConnection.getConnection().createStatement()) {
            statement.executeUpdate(CREATE_DATABASE_QUERY);
            statement.executeUpdate(CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void dropDatabase() {
        try (Statement statement = SQLConnection.getConnection().createStatement()) {
            statement.executeUpdate(DROP_DATABASE_QUERY);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
