package org.example.lesson8.dao.impl;


import org.example.lesson8.dao.DoorDAO;
import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.utils.wrappers.ThrowingConsumerWrapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.lesson8.dao.impl.MockConstants.*;
import static org.example.lesson8.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DoorDAOImplTest {
    private static final DoorDAO DOOR_DAO = new DoorDAOImpl();
    private static final List<DoorDTO> doorDTOS = new ArrayList<>();

    @BeforeAll
    public static void createDB() {
        doorDTOS.add(MockUtils.buildDoor(DOORS_SIZE.get(0), DOORS_TYPE.get(0)));
        doorDTOS.add(MockUtils.buildDoor(DOORS_SIZE.get(1), DOORS_TYPE.get(1)));
        doorDTOS.add(MockUtils.buildDoor(DOORS_SIZE.get(2), DOORS_TYPE.get(2)));
        doorDTOS.add(MockUtils.buildDoor(DOORS_SIZE.get(3), DOORS_TYPE.get(3)));
        doorDTOS.add(MockUtils.buildDoor(DOORS_SIZE.get(4), DOORS_TYPE.get(4)));
    }

    @Test
    public void getBySizeTest() {
        try {
            doorDTOS.forEach(ThrowingConsumerWrapper.accept(d -> DOOR_DAO.save(d, DoorDTO.class), SQLException.class));

            List<DoorDTO> doorDTOList = DOOR_DAO.getBySize(FROM_SIZE, TO_SIZE);
            int expected = 3;
            assertNotNull(doorDTOList);
            assertEquals(expected, doorDTOList.size());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}