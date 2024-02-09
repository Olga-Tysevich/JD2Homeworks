package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.AddressDAO;
import org.example.lesson9.dto.Address;
import org.example.lesson9.utils.JsonManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.lesson9.utils_src.MockConstants.ADDRESSES_JSON;
import static org.example.lesson9.utils_src.MockConstants.TEST_UPDATE;
import static org.junit.jupiter.api.Assertions.*;

class AddressDAOImplTest {
    private final AddressDAO addressDAO = new AddressDAOImpl();

    @Test
    public void saveTest() {
        try {
            List<Address> expected = JsonManager.readDTOList(ADDRESSES_JSON, Address.class);
            expected.forEach(addressDAO::save);
            List<Address> actual = expected.stream()
                    .peek(a -> addressDAO.get(a.getId()))
                    .collect(Collectors.toList());

            assertEquals(expected.get(0), actual.get(0));
            assertEquals(expected.get(1), actual.get(1));
            assertEquals(expected.get(2), actual.get(2));
            assertEquals(expected.get(3), actual.get(3));
            assertEquals(expected.get(4), actual.get(4));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateTest() {
        try {
            List<Address> actual = JsonManager.readDTOList(ADDRESSES_JSON, Address.class);
            actual.forEach(addressDAO::save);
            List<String> expected = actual.stream()
                    .map(a -> a.getStreet() + TEST_UPDATE)
                    .collect(Collectors.toList());
            actual.forEach(a -> a.setStreet(a.getStreet() + TEST_UPDATE));
            actual.forEach(addressDAO::update);


            assertEquals(expected.get(0), actual.get(0).getStreet());
            assertEquals(expected.get(1), actual.get(1).getStreet());
            assertEquals(expected.get(2), actual.get(2).getStreet());
            assertEquals(expected.get(3), actual.get(3).getStreet());
            assertEquals(expected.get(4), actual.get(4).getStreet());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTest() {
        try {
            List<Address> expected = JsonManager.readDTOList(ADDRESSES_JSON, Address.class);
            expected.forEach(addressDAO::save);
            expected.forEach(a -> addressDAO.delete(a.getId()));
            List<Address> actual = expected.stream()
                    .map(a -> addressDAO.get(a.getId()))
                    .collect(Collectors.toList());
            addressDAO.delete(0);

            assertNull(actual.get(0));
            assertNull(actual.get(1));
            assertNull(actual.get(2));
            assertNull(actual.get(3));
            assertNull(actual.get(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}