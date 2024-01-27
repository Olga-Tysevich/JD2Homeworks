package org.example.lesson8.dao.impl;

import org.example.lesson8.dao.DAO;
import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.dto.PersonDTO;
import org.example.lesson8.utils.TableManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.stream.Stream;

import static org.example.lesson8.dao.impl.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class DAOImplTest<T> {
    private final DAO<T> DAO = new DAOImpl<>();

    @AfterAll
    public static void dropDB() {
        TableManager.dropDatabase(PEOPLE_DATABASE);
        TableManager.dropDatabase(CITY_DATABASE);
    }

    @ParameterizedTest()
    @MethodSource("cases")
    public void saveTest(T expected, Class<T> clazz, String databaseName, String query) {
        try {
            TableManager.dropDatabase(databaseName);
            TableManager.createDatabase(databaseName);
            TableManager.createTable(query);

            if (expected != null) {
                T actual = DAO.save(expected, clazz);
                assertNotNull(actual);
            } else {
                assertThrows(IllegalArgumentException.class, () -> DAO.save(expected, clazz));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @ParameterizedTest()
    @MethodSource("cases")
    public void updateTest(T dto, Class<T> clazz, String databaseName, String query) {
        try {
            TableManager.dropDatabase(databaseName);
            TableManager.createDatabase(databaseName);
            TableManager.createTable(query);
            int expectedRows = 1;

            if (dto != null) {
                T dto2 = DAO.save(dto, clazz);

                int actualRows = DAO.update(dto2);

                assertEquals(expectedRows, actualRows);
            } else {
                assertThrows(IllegalArgumentException.class, () -> DAO.save(dto, clazz));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @ParameterizedTest()
    @MethodSource("cases")
    public void deleteTest(T dto, Class<T> clazz, String databaseName, String query) {
        TableManager.dropDatabase(databaseName);
        TableManager.createDatabase(databaseName);
        TableManager.createTable(query);
        try {
            if (dto != null) {
                DAO.save(dto, clazz);
                int deletedRow = DAO.delete(1, clazz);
                int expected = 1;

                assertEquals(expected, deletedRow);
            } else {
                assertThrows(IllegalArgumentException.class, () -> DAO.save(dto, clazz));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(MockUtils.buildPerson(PERSONS_AGE.get(0), PERSONS_SALARY.get(0), PERSONS_PASSPORT.get(0),
                        PERSONS_ADDRESS.get(0), PERSONS_BIRTHDAY.get(0), PERSONS_LUNCH.get(0), PERSONS_LETTER.get(0)), PersonDTO.class,
                        PEOPLE_DATABASE, CREATE_TABLE_PERSON),

                Arguments.of(MockUtils.buildPerson(PERSONS_AGE.get(1), PERSONS_SALARY.get(1), PERSONS_PASSPORT.get(1),
                        PERSONS_ADDRESS.get(1), PERSONS_BIRTHDAY.get(1), PERSONS_LUNCH.get(1), PERSONS_LETTER.get(1)), PersonDTO.class,
                        PEOPLE_DATABASE, CREATE_TABLE_PERSON),

                Arguments.of(MockUtils.buildPerson(PERSONS_AGE.get(2), PERSONS_SALARY.get(2), PERSONS_PASSPORT.get(2),
                        PERSONS_ADDRESS.get(2), PERSONS_BIRTHDAY.get(2), PERSONS_LUNCH.get(2), PERSONS_LETTER.get(2)), PersonDTO.class,
                        PEOPLE_DATABASE, CREATE_TABLE_PERSON),

                Arguments.of(MockUtils.buildPerson(PERSONS_AGE.get(3), PERSONS_SALARY.get(3), PERSONS_PASSPORT.get(3),
                        PERSONS_ADDRESS.get(3), PERSONS_BIRTHDAY.get(3), PERSONS_LUNCH.get(3), PERSONS_LETTER.get(3)), PersonDTO.class,
                        PEOPLE_DATABASE, CREATE_TABLE_PERSON),

                Arguments.of(MockUtils.buildPerson(PERSONS_AGE.get(4), PERSONS_SALARY.get(4), PERSONS_PASSPORT.get(4),
                        PERSONS_ADDRESS.get(4), PERSONS_BIRTHDAY.get(4), PERSONS_LUNCH.get(4), PERSONS_LETTER.get(4)), PersonDTO.class,
                        PEOPLE_DATABASE, CREATE_TABLE_PERSON),

                Arguments.of(null, PersonDTO.class, PEOPLE_DATABASE, CREATE_TABLE_PERSON),

                Arguments.of(MockUtils.buildDoor(DOORS_SIZE.get(0), DOORS_TYPE.get(0)), DoorDTO.class, CITY_DATABASE, CREATE_TABLE_HOUSES),

                Arguments.of(MockUtils.buildDoor(DOORS_SIZE.get(1), DOORS_TYPE.get(1)), DoorDTO.class, CITY_DATABASE, CREATE_TABLE_HOUSES),

                Arguments.of(MockUtils.buildDoor(DOORS_SIZE.get(2), DOORS_TYPE.get(2)), DoorDTO.class, CITY_DATABASE, CREATE_TABLE_HOUSES),

                Arguments.of(MockUtils.buildDoor(DOORS_SIZE.get(3), DOORS_TYPE.get(3)), DoorDTO.class, CITY_DATABASE, CREATE_TABLE_HOUSES),

                Arguments.of(MockUtils.buildDoor(DOORS_SIZE.get(4), DOORS_TYPE.get(4)), DoorDTO.class, CITY_DATABASE, CREATE_TABLE_HOUSES),

                Arguments.of(null, DoorDTO.class, CITY_DATABASE, CREATE_TABLE_HOUSES)
        );
    }

}