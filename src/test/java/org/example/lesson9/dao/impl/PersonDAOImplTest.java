package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.PersonDAO;
import org.example.lesson9.dto.PersonDTO;
import org.example.lesson9.utils.HibernateUtil;
import org.example.lesson9.utils_src.MockUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.example.lesson9.utils_src.MockConstants.DELETE_ALL_PERSONS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonDAOImplTest {
    private final PersonDAO personDAO = new PersonDAOImpl();

    @AfterAll
    public static void deletePersons() {
        EntityManager manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery(DELETE_ALL_PERSONS);
        query.executeUpdate();
        manager.getTransaction().commit();
        manager.close();
    }

    @Test
    public void increaseAgeTest() {
        PersonDTO personDTO = MockUtils.buildPerson();
        personDAO.save(personDTO);
        int id = personDTO.getId();
        int expected = personDTO.getAge() + 1;
        personDAO.increaseAge(id, 1);
        int actual = personDAO.get(id).getAge();

        assertEquals(expected, actual);
    }

}