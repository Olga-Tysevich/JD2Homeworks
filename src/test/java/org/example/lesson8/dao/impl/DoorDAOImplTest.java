package org.example.lesson8.dao.impl;


import org.example.lesson8.dao.DoorDAO;
import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.utils.wrappers.ThrowingConsumerWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.lesson8.dao.impl.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

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
            MockUtils.createDatabase(DATABASE);
            MockUtils.createTable(CREATE_TABLE_DOORS);
            List<DoorDTO> forDelete = new ArrayList<>();
            doorDTOS.forEach(ThrowingConsumerWrapper.accept(d -> forDelete.add(DOOR_DAO.save(d, DoorDTO.class)), SQLException.class));

            List<DoorDTO> doorDTOList = DOOR_DAO.getBySize(FROM_SIZE, TO_SIZE);
            int expected = 3;
            forDelete.forEach(this::deleteTestDoor);
            assertNotNull(doorDTOList);
            assertTrue(doorDTOList.size() >= expected, "Expected: " + expected + ", actual: " + doorDTOList.size());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void deleteTestDoor(DoorDTO doorDTO)  {

        Object idForDelete = MockUtils.getId(doorDTO);
        if (idForDelete != null) {
            try {
                DOOR_DAO.delete((int) idForDelete, DoorDTO.class);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}