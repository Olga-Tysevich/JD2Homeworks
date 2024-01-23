package org.example.lesson7.dao;


import org.example.lesson7.connection.SQLConnection;
import org.example.lesson7.dto.PersonDTO;
import org.example.lesson7.utils.ThrowingConsumerWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonDAOImpl implements PersonDAO {
    private final String insertPerson = "insert into people.person(age, salary, passport, address, "
            + "date_of_birthday,  time_to_lunch, letter) values (?, ?, ?, ?, ?, ?, ?)";
    private final String updateQuery = "update people.person set age = ?, salary = ?, passport = ?, address = ?, "
            + "date_of_birthday = ?, time_to_lunch = ?, letter = ? where id = ?";
    private final String selectById = "select * from people.person where id = ?";
    private final String selectAll = "select * from people.person";
    private final String deleteById = "delete from people.person where id = ?";
    private final String insertQueryPattern = "insert [\\w\\p{P}\\sа-яА-Я]+";

    @Override
    public PersonDTO save(PersonDTO person) throws SQLException {
        try (PreparedStatement statement = createUpdateQuery(insertPerson, person)) {
            int affectedRow = statement.executeUpdate();
            int personDTOId = getPersonId(statement, affectedRow);
            person.setId(personDTOId);
        }
        return person;
    }

    public int saveAll(List<PersonDTO> personDTOList) throws SQLException {
        Connection connection = SQLConnection.getConnection();
        connection.setAutoCommit(false);
        int[] insertRows;
        try (Statement statement= connection.createStatement()){
            personDTOList.forEach(ThrowingConsumerWrapper.accept(
                    p -> statement.addBatch(
                            getQuery(insertQueryPattern, createUpdateQuery(insertPerson, p).toString())),
                    SQLException.class));
            insertRows = statement.executeBatch();
            connection.commit();
        }
        connection.setAutoCommit(true);
        return Arrays.stream(insertRows).sum();
    }

    @Override
    public int update(PersonDTO person) throws SQLException {
        int result;
        try (PreparedStatement statement = createUpdateQuery(updateQuery, person)) {
            statement.setInt(8, person.getId());
            result = statement.executeUpdate();
        }
        return result;
    }

    private String getQuery(String regex, String preparedStatement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(preparedStatement);
        if (matcher.find()) {
            return preparedStatement.substring(matcher.start(), matcher.end());
        }
        return null;
    }

    private PreparedStatement createUpdateQuery(String query, PersonDTO person) throws SQLException {
        PreparedStatement statement = SQLConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, person.getAge());
        statement.setDouble(2, person.getSalary());
        statement.setString(3, person.getPassport());
        statement.setString(4, person.getAddress());
        statement.setDate(5, new Date(person.getDateOfBirthday().getTime()));
        statement.setTime(6, person.getTimeToLunch());
        statement.setString(7, person.getLetter());
        return statement;
    }

    private int getPersonId(PreparedStatement statement, int affectedRow) throws SQLException {
        int id = 0;
        if (affectedRow !=0) {
            try (ResultSet idResult = statement.getGeneratedKeys()){
                while (idResult.next()) {
                    if (idResult.last()){
                        id = idResult.getInt(1);
                    }
                }
            }
        }
        return id;
    }

    @Override
    public PersonDTO get(int id) throws SQLException {
        try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(selectById)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? buildPersonDTO(resultSet) : null;
            }
        }
    }

    @Override
    public int delete(PersonDTO person) throws SQLException {
        int result;
        try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(deleteById)) {
            statement.setInt(1, person.getId());
            result = statement.executeUpdate();
        }
        return result;
    }

    @Override
    public List<PersonDTO> getAll() throws SQLException {
        List<PersonDTO> personList = new ArrayList<>();
        try (Statement statement = SQLConnection.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(selectAll)) {
                while (resultSet.next()) {
                    personList.add(buildPersonDTO(resultSet));
                }
            }
        }
        return personList;
    }

    private PersonDTO buildPersonDTO(ResultSet resultSet) throws SQLException {
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
