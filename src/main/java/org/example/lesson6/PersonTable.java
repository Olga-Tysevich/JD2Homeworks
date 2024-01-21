package org.example.lesson6;

import org.example.lesson6.connection.SQLConnector;
import org.example.lesson6.dto.PersonDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonTable {
    private static final String INSERT_PERSON_QUERY = "insert into people.person" +
            "(age, salary, passport, address, date_of_birthday,  time_to_lunch, letter) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PERSON_OVER_21_BY_DATE_TIME_CREATE_QUERY = "select * from people.person where age > 21 order by date_time_create";


    public static PersonDTO insertPerson(PersonDTO personDTO) throws SQLException {
        try (PreparedStatement statement = SQLConnector.getConnection()
                .prepareStatement(INSERT_PERSON_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, personDTO.getAge());
            statement.setDouble(2, personDTO.getSalary());
            statement.setString(3, personDTO.getPassport());
            statement.setString(4, personDTO.getAddress());
            statement.setDate(5, new Date(personDTO.getDateOfBirthday().getTime()));
            statement.setTime(6, personDTO.getTimeToLunch());
            statement.setString(7, personDTO.getLetter());
            personDTO.setId(getPersonId(statement));
        }
        return personDTO;
    }


    public static List<PersonDTO> getPersonOver21ByDateTimeCreate() throws SQLException {
        List<PersonDTO> result = new ArrayList<>();
        try (Statement statement = SQLConnector.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_PERSON_OVER_21_BY_DATE_TIME_CREATE_QUERY);
            while (resultSet.next()) {
                result.add(buildPersonDTO(resultSet));
            }
            resultSet.close();
        }
        return result;
    }

    private static int getPersonId(PreparedStatement statement) throws SQLException {
        int affectedRow = statement.executeUpdate();
        int id = 0;
        if (affectedRow != 0) {
            try (ResultSet idResult = statement.getGeneratedKeys()) {
                while (idResult.next()) {
                    if (idResult.last()) {
                        id = idResult.getInt(1);
                    }
                }
            }
        }
        return id;
    }

    private static PersonDTO buildPersonDTO(ResultSet resultSet) throws SQLException {
        return PersonDTO.builder()
                .id(resultSet.getInt(1))
                .age(resultSet.getInt(2))
                .salary(resultSet.getDouble(3))
                .passport(resultSet.getString(4))
                .address(resultSet.getString(5))
                .dateOfBirthday(resultSet.getDate(6))
                .dateTimeCreate(resultSet.getTimestamp(7))
                .timeToLunch(resultSet.getTime(8))
                .letter(resultSet.getString(9))
                .build();
    }

}
