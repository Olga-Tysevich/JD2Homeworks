package org.example.lesson8.dao.impl;

import org.example.lesson8.dao.PersonDAO;
import org.example.lesson8.dto.PersonDTO;
import org.example.lesson8.utils.TableManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.stream.Stream;

import static org.example.lesson8.dao.impl.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonDAOImplTest {
    private static final PersonDAO PERSON_DAO = new PersonDAOImpl();

    @BeforeAll
    public static void createDB() {
        TableManager.createDatabase(PEOPLE_DATABASE);
        TableManager.createTable(CREATE_TABLE_PERSON);
    }

    @AfterAll
    public static void dropDB() {
        TableManager.dropDatabase(PEOPLE_DATABASE);
    }

    @ParameterizedTest()
    @MethodSource("personCases")
    public void saveTest(PersonDTO expected, Class<PersonDTO> clazz) {
        try {

            if (expected != null) {
                PersonDTO actual = PERSON_DAO.save(expected, clazz);
                assertNotNull(actual);
                assertNotEquals(expected.getId(), actual.getId());
                assertEquals(expected.getAge(), actual.getAge());
                assertEquals(expected.getSalary(), actual.getSalary());
                assertEquals(expected.getPassport(), actual.getPassport());
                assertEquals(expected.getAddress(), actual.getAddress());
                assertEquals(expected.getDateOfBirthday(), actual.getDateOfBirthday());
                assertEquals(expected.getTimeToLunch(), actual.getTimeToLunch());
                assertNotEquals(expected.getDateTimeCreate(), actual.getDateTimeCreate());
                assertEquals(expected.getLetter(), actual.getLetter());
                assertNotEquals(expected.getId(), actual.getId());
            } else {
                assertThrows(IllegalArgumentException.class, () -> PERSON_DAO.save(expected, clazz));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @ParameterizedTest()
    @MethodSource("personCases")
    public void updateTest(PersonDTO personDTO, Class<PersonDTO> clazz) {
        try {
            TableManager.dropDatabase(PEOPLE_DATABASE);
            TableManager.createDatabase(PEOPLE_DATABASE);
            TableManager.createTable(CREATE_TABLE_PERSON);
            int expectedID = 1;

            if (personDTO != null) {
                PERSON_DAO.save(personDTO, PersonDTO.class);
                String expectedLetter = "another";
                personDTO.setId(expectedID);
                personDTO.setLetter(expectedLetter);
                int actualId = PERSON_DAO.update(personDTO);
                String actual = PERSON_DAO.get(expectedID, PersonDTO.class).getLetter();
                assertEquals(expectedID, actualId);
                assertEquals(expectedLetter, actual);
            } else {
                assertThrows(IllegalArgumentException.class, () -> PERSON_DAO.save(personDTO, clazz));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @ParameterizedTest()
    @MethodSource("personCases")
    public void deleteTest(PersonDTO personDTO) {
        try {

            if (personDTO != null) {
                int id = PERSON_DAO.save(personDTO, PersonDTO.class).getId();
                int expected = PERSON_DAO.delete(id, PersonDTO.class);
                PersonDTO actual = PERSON_DAO.get(id, PersonDTO.class);

                assertEquals(expected, 1);
                assertNull(actual);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @ParameterizedTest()
    @MethodSource("personCases")
    public void getByPassportTest(PersonDTO personDTO) {
        try {

            if (personDTO != null) {
                PersonDTO expected = PERSON_DAO.save(personDTO, PersonDTO.class);
                PersonDTO actual = PERSON_DAO.getByPassport(personDTO.getPassport());

                assertEquals(expected, actual);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static Stream<Arguments> personCases() {
        return Stream.of(
                Arguments.of(MockUtils.buildPerson(PERSONS_AGE.get(0), PERSONS_SALARY.get(0), PERSONS_PASSPORT.get(0),
                        PERSONS_ADDRESS.get(0), PERSONS_BIRTHDAY.get(0), PERSONS_LUNCH.get(0), PERSONS_LETTER.get(0)), PersonDTO.class),

                Arguments.of(MockUtils.buildPerson(PERSONS_AGE.get(1), PERSONS_SALARY.get(1), PERSONS_PASSPORT.get(1),
                        PERSONS_ADDRESS.get(1), PERSONS_BIRTHDAY.get(1), PERSONS_LUNCH.get(1), PERSONS_LETTER.get(1)), PersonDTO.class),

                Arguments.of(MockUtils.buildPerson(PERSONS_AGE.get(2), PERSONS_SALARY.get(2), PERSONS_PASSPORT.get(2),
                        PERSONS_ADDRESS.get(2), PERSONS_BIRTHDAY.get(2), PERSONS_LUNCH.get(2), PERSONS_LETTER.get(2)), PersonDTO.class),

                Arguments.of(MockUtils.buildPerson(PERSONS_AGE.get(3), PERSONS_SALARY.get(3), PERSONS_PASSPORT.get(3),
                        PERSONS_ADDRESS.get(3), PERSONS_BIRTHDAY.get(3), PERSONS_LUNCH.get(3), PERSONS_LETTER.get(3)), PersonDTO.class),

                Arguments.of(MockUtils.buildPerson(PERSONS_AGE.get(4), PERSONS_SALARY.get(4), PERSONS_PASSPORT.get(4),
                        PERSONS_ADDRESS.get(4), PERSONS_BIRTHDAY.get(4), PERSONS_LUNCH.get(4), PERSONS_LETTER.get(4)), PersonDTO.class),

                Arguments.of(null, PersonDTO.class)
        );
    }
}