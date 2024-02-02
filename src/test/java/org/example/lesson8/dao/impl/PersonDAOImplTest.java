package org.example.lesson8.dao.impl;


import org.example.lesson8.dao.PersonDAO;
import org.example.lesson8.dto.Person;
import org.example.lesson8.utils.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.example.lesson8.dao.impl.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonDAOImplTest {
    private final PersonDAO DAO = new PersonDAOImpl();

    @Test
    void getPersonsOverAge() {
        DAO.saveAll(MockUtils.getPersons());
        List<Person> actual = DAO.getPersonsOverAge(21);

        assertEquals(actual.size(), 3);
    }

    @Test
    void save() {
        Person person = MockUtils.getPerson();
        int oldId = person.getId();
        Timestamp oldDateCreate = person.getDateTimeCreate();
        DAO.save(person);

        assertNotEquals(oldId, person.getId(), "id before save: " + oldId + ", id after save: " + person.getId());
        assertNotEquals(oldDateCreate, person.getDateTimeCreate(), "date create before save: " + oldDateCreate
                + ", id after save: " + person.getDateTimeCreate());
    }

    @Test
    void get() {
        Person person = MockUtils.getPerson();
        person.setPassport(person.getPassport());
        DAO.save(person);
        Person actual = DAO.get(person.getId(), Person.class);

        assertEquals(person.getId(), actual.getId(), "expected id: " + person.getId() + ", actual: " + actual.getId());
        assertEquals(AGE, actual.getAge(), "expected age: " + AGE + ", actual: " + actual.getAge());
        assertEquals(SALARY, actual.getSalary(), "expected salary: " + SALARY + ", actual: " + actual.getSalary());
        assertEquals(PASSPORT, actual.getPassport(), "expected passport: " + PASSPORT + ", actual: " + actual.getPassport());
        assertEquals(ADDRESS, actual.getAddress(), "expected address: " + ADDRESS + ", actual: " + actual.getAddress());
        assertEquals(BIRTHDAY, actual.getDateOfBirthday(), "expected birthday: " + BIRTHDAY
                + ", actual: " + actual.getDateOfBirthday());
        assertEquals(person.getDateTimeCreate(), person.getDateTimeCreate(), "expected birthday: " + person.getDateOfBirthday()
                + ", actual: " + actual.getDateOfBirthday());
        assertEquals(LUNCH, actual.getTimeToLunch(), "expected lunch time: " + LUNCH + ", actual: " + actual.getTimeToLunch());
    }

    @Test
    void update() {
        Person person = MockUtils.getPerson();
        DAO.save(person);
        String expected = person.getAddress() + " new";
        person.setAddress(expected);
        DAO.update(person);
        String actual = DAO.get(person.getId(), Person.class).getAddress();

        assertEquals(expected, actual, "address expected: " + expected + ", actual: " + actual);
    }

    @Test
    void delete() {
        Person person = MockUtils.getPerson();
        DAO.save(person);
        int randomId = person.getId();
        DAO.delete(randomId, Person.class);

        assertNull(DAO.get(randomId, Person.class));
    }

    @AfterAll
    static void close() {
        HibernateUtil.close();
    }
}