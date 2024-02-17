package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.PersonDAO;
import org.example.lesson9.dto.PersonDTO;
import org.example.lesson9.utils.HibernateUtil;
import org.example.lesson9.utils.JsonManager;
import org.example.lesson9.utils_src.MockUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.lesson9.utils_src.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonDAOImplTest {
    private List<PersonDTO> personDTOList;
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

    @BeforeEach
    public void readList() {
        try {
            personDTOList = JsonManager.readDTOList(PERSONS_JSON, PersonDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveTest() {
        personDTOList.forEach(personDAO::save);
        List<PersonDTO> actual = personDTOList.stream()
                .peek(p -> personDAO.get(p.getId()))
                .collect(Collectors.toList());

        assertEquals(personDTOList.get(0), actual.get(0));
        assertEquals(personDTOList.get(1), actual.get(1));
        assertEquals(personDTOList.get(2), actual.get(2));
        assertEquals(personDTOList.get(3), actual.get(3));
        assertEquals(personDTOList.get(4), actual.get(4));
    }

    @Test
    public void updateTest() {
        List<String> expected = personDTOList.stream()
                .map(p -> p.getName() + TEST_UPDATE)
                .collect(Collectors.toList());

        personDTOList.forEach(p -> p.setName(p.getName() + TEST_UPDATE));
        personDTOList.forEach(personDAO::update);

        assertEquals(expected.get(0), personDTOList.get(0).getName());
        assertEquals(expected.get(1), personDTOList.get(1).getName());
        assertEquals(expected.get(2), personDTOList.get(2).getName());
        assertEquals(expected.get(3), personDTOList.get(3).getName());
        assertEquals(expected.get(4), personDTOList.get(4).getName());
    }

    @Test
    public void deleteTest() {
        personDTOList.forEach(p -> personDAO.delete(p.getId()));
        List<PersonDTO> actual = personDTOList.stream()
                .map(p -> personDAO.get(p.getId()))
                .collect(Collectors.toList());
        personDAO.delete(0);

        assertNull(actual.get(0));
        assertNull(actual.get(1));
        assertNull(actual.get(2));
        assertNull(actual.get(3));
        assertNull(actual.get(4));
    }

    @Test
    public void increaseAgeTest() {
        PersonDTO personDTO = MockUtils.buildPerson();
        int expected = personDTO.getAge() + 1;
        personDAO.save(personDTO);
        int id = personDTO.getId();
        personDAO.increaseAge(id, 1);
        int actual = personDAO.get(id).getAge();

        assertEquals(expected, actual);
    }

}