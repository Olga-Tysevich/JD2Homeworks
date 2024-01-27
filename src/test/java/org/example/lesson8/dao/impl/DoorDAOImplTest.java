package org.example.lesson8.dao.impl;


import org.example.lesson8.dao.DAO;
import org.example.lesson8.dao.DoorDAO;
import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.dto.PersonDTO;
import org.example.lesson8.utils.TableManager;
import org.example.lesson8.utils.wrappers.ThrowingConsumerWrapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.example.lesson8.dao.impl.MockConstants.*;
import static org.example.lesson8.utils.Constants.*;
import static org.example.lesson8.utils.Constants.RANDOM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DoorDAOImplTest {
    private static final DoorDAO DOOR_DAO = new DoorDAOImpl();
    private static List<DoorDTO> doorDTOS;

    @BeforeAll
    public static void createDB() {
        TableManager.createDatabase(DOOR_DATABASE);
        TableManager.createTable(CREATE_TABLE_DOORS);
        doorDTOS = IntStream.range(0, NUMBER_OF_DOORS)
        .mapToObj(i -> MockUtils.buildDoor(DOORS_SIZE.get(RANDOM.nextInt(NUMBER_OF_DOORS)), DOORS_TYPE.get(RANDOM.nextInt(NUMBER_OF_DOORS))))
        .collect(Collectors.toList());
    }

    @AfterAll
    public static void dropDB() {
        TableManager.dropDatabase(DOOR_DATABASE);
    }

    @Test
    public void getAllTest() {
        try {
            doorDTOS.forEach(ThrowingConsumerWrapper.accept(d -> DOOR_DAO.save(d, DoorDTO.class), SQLException.class));

            List<DoorDTO> doorDTOList = DOOR_DAO.getAll();
            assertNotNull(doorDTOList);
            assertEquals(doorDTOList.size(), doorDTOS.size());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}