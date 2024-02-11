package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.AddressDAO;
import org.example.lesson9.dao.PersonDAO;
import org.example.lesson9.dto.AddressDTO;
import org.example.lesson9.dto.PersonDTO;
import org.example.lesson9.utils.HibernateUtil;
import org.example.lesson9.utils.JsonManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.lesson9.utils_src.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class AddressDAOImplTest {
    private static List<AddressDTO> addressDTOS;
    private static List<PersonDTO> personDTOList;
    private final AddressDAO addressDAO = new AddressDAOImpl();
    private final PersonDAO personDAO = new PersonDAOImpl();

    @BeforeAll
    public static void readList() {
        try {
            addressDTOS = JsonManager.readDTOList(ADDRESSES_JSON, AddressDTO.class);
            personDTOList = JsonManager.readDTOList(PERSONS_JSON, PersonDTO.class);
            addressDTOS.forEach(a -> a.setPeople(new HashSet<>(personDTOList)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveTest() {
        addressDTOS.forEach(addressDAO::save);
        List<AddressDTO> actual = addressDTOS.stream()
                .peek(a -> addressDAO.get(a.getId()))
                .collect(Collectors.toList());

        assertEquals(addressDTOS.get(0), actual.get(0));
        assertEquals(addressDTOS.get(1), actual.get(1));
        assertEquals(addressDTOS.get(2), actual.get(2));
        assertEquals(addressDTOS.get(3), actual.get(3));
        assertEquals(addressDTOS.get(4), actual.get(4));
    }

    @Test
    public void updateTest() {
        List<String> expected = addressDTOS.stream()
                .map(a -> a.getStreet() + TEST_UPDATE)
                .collect(Collectors.toList());

        addressDTOS.forEach(a -> a.setStreet(a.getStreet() + TEST_UPDATE));
        addressDTOS.forEach(addressDAO::update);

        assertEquals(expected.get(0), addressDTOS.get(0).getStreet());
        assertEquals(expected.get(1), addressDTOS.get(1).getStreet());
        assertEquals(expected.get(2), addressDTOS.get(2).getStreet());
        assertEquals(expected.get(3), addressDTOS.get(3).getStreet());
        assertEquals(expected.get(4), addressDTOS.get(4).getStreet());
    }

    @Test
    public void deleteTest() {
        addressDTOS.stream()
                .peek(a -> a.getPeople().forEach(p -> personDAO.delete(p.getId())))
                .forEach(a -> addressDAO.delete(a.getId()));
        List<AddressDTO> actual = addressDTOS.stream()
                .map(a -> addressDAO.get(a.getId()))
                .collect(Collectors.toList());
        addressDAO.delete(0);

        assertNull(actual.get(0));
        assertNull(actual.get(1));
        assertNull(actual.get(2));
        assertNull(actual.get(3));
        assertNull(actual.get(4));
    }

    @Test
    public void increaseHouseNumberTest() {
        int id = addressDTOS.get(0).getId();
        int expected = addressDTOS.get(0).getHouse() + 1;
        addressDAO.increaseHouseNumber(id, 1);
        int actual = addressDAO.get(id).getHouse();

        assertEquals(expected, actual);
    }

    @AfterAll
    public static void deleteAll() {
        EntityManager manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        Query deletePeopleQuery = manager.createNativeQuery(DELETE_ALL_PEOPLE);
        deletePeopleQuery.executeUpdate();
        manager.flush();
        Query deleteAddressesQuery = manager.createNativeQuery(DELETE_ALL_ADDRESSES);
        deleteAddressesQuery.executeUpdate();
        manager.getTransaction().commit();
        manager.close();
    }

}