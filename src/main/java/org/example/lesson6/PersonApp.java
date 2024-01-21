package org.example.lesson6;

import org.example.lesson6.connection.SQLConnector;
import org.example.lesson6.dto.PersonDTO;
import org.example.lesson6.utils.DatabasePrinter;
import org.example.lesson6.utils.GsonManager;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PersonApp {
    private static final String PERSONS_FILE_PATH = "src\\main\\resources\\persons.json";
    private static final String PERSONS_OVER_21_FILE_PATH = "src\\main\\resources\\personsOver21.json";
    private static final String DATABASE_NAME = "people";
    private static final String TABLE_NAME = "person";
    private static final String CREATE_PEOPLE_DATABASE = "create database people";
    private static final String CREATE_PERSON_TABLE = "create table people.person("
            + "id INT PRIMARY KEY AUTO_INCREMENT, "
            + "age INT, salary DECIMAL, "
            + "passport CHAR(10), "
            + "address VARCHAR(200), "
            + "date_of_birthday DATE,"
            + "date_time_create TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
            + "time_to_lunch TIME, "
            + "letter TEXT)";
    private static final String DROP_PEOPLE_DATABASE = "drop database people";

    public static void main(String[] args) throws SQLException, IOException {
        try {
            createDatabase();
            createTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        List<PersonDTO> personsDTOFromJson = GsonManager.readAllPersonDTO(PERSONS_FILE_PATH);
        System.out.println("\nPersonDTO from json:");
        personsDTOFromJson.forEach(System.out::println);

        System.out.println("\nPersonDTO from person table:");
        personsDTOFromJson.stream().map(p -> {
            try {
                return PersonTable.insertPerson(p);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }).forEach(System.out::println);

        System.out.println("\nPersonDTO over 21:");
        List<PersonDTO> personDTOList = PersonTable.getPersonOver21ByDateTimeCreate();
        personDTOList.forEach(System.out::println);

        GsonManager.writeAllPersonDTO(PERSONS_OVER_21_FILE_PATH, personDTOList);

        System.out.println();
        DatabasePrinter.printAllSchemas();
        DatabasePrinter.printTableMeta(DATABASE_NAME, TABLE_NAME);

        dropDatabase(); //Это просто для меня, что бы одно задание не мешало тренироваться в другом =)
        SQLConnector.closeConnection();
    }

    public static void createDatabase() throws SQLException {
        executeUpdateQuery(CREATE_PEOPLE_DATABASE);
    }

    public static void createTable() throws SQLException {
        executeUpdateQuery(CREATE_PERSON_TABLE);
    }

    public static void dropDatabase() throws SQLException {
        executeUpdateQuery(DROP_PEOPLE_DATABASE);
    }

    private static void executeUpdateQuery(String query) throws SQLException {
        try (Statement statement = SQLConnector.getConnection().createStatement()) {
            statement.executeUpdate(query);
        }
    }
}
