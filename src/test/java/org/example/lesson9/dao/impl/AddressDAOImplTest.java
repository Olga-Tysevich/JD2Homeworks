package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.AddressDAO;
import org.example.lesson9.dto.AddressDTO;
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

class AddressDAOImplTest {
    private List<AddressDTO> addressDTOS;
    private final AddressDAO addressDAO = new AddressDAOImpl();

    @AfterAll
    public static void deleteAll() {
        EntityManager manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery(DELETE_ALL_ADDRESSES);
        query.executeUpdate();
        manager.getTransaction().commit();
        manager.close();
    }

    @BeforeEach
    public void readList() {
        try {
            addressDTOS = JsonManager.readDTOList(ADDRESSES_JSON, AddressDTO.class);
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
        addressDTOS.forEach(a -> addressDAO.delete(a.getId()));
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
        AddressDTO addressDTO = MockUtils.buildAddress();
        addressDAO.save(addressDTO);
        int id = addressDTO.getId();
        int expected = addressDTO.getHouse() + 1;
        addressDAO.increaseHouseNumber(id, 1);
        int actual = addressDAO.get(id).getHouse();

        assertEquals(expected, actual);
    }

}