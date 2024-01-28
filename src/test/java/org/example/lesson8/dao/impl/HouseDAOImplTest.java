package org.example.lesson8.dao.impl;


import org.example.lesson8.dao.HouseDAO;
import org.example.lesson8.dto.HouseDTO;
import org.example.lesson8.utils.TableManager;
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

class HouseDAOImplTest {
    private static final HouseDAO HOUSE_DAO = new HouseDAOImpl();
    private static final List<HouseDTO> houseDTOS = new ArrayList<>();

    @BeforeAll
    public static void createDB() {
        TableManager.createDatabase(CITY_DATABASE);
        TableManager.createTable(CREATE_TABLE_HOUSES);
        houseDTOS.add(MockUtils.buildHouse(HOUSES_SIZE.get(0), HOUSES_COLOR.get(0), HOUSES_ROOM.get(0)));
        houseDTOS.add(MockUtils.buildHouse(HOUSES_SIZE.get(0), HOUSES_COLOR.get(0), HOUSES_ROOM.get(0)));
        houseDTOS.add(MockUtils.buildHouse(HOUSES_SIZE.get(1), HOUSES_COLOR.get(1), HOUSES_ROOM.get(1)));
        houseDTOS.add(MockUtils.buildHouse(HOUSES_SIZE.get(1), HOUSES_COLOR.get(1), HOUSES_ROOM.get(1)));
        houseDTOS.add(MockUtils.buildHouse(HOUSES_SIZE.get(1), HOUSES_COLOR.get(1), HOUSES_ROOM.get(1)));
        houseDTOS.add(MockUtils.buildHouse(HOUSES_SIZE.get(2), HOUSES_COLOR.get(2), HOUSES_ROOM.get(2)));
        houseDTOS.add(MockUtils.buildHouse(HOUSES_SIZE.get(3), HOUSES_COLOR.get(3), HOUSES_ROOM.get(3)));
        houseDTOS.add(MockUtils.buildHouse(HOUSES_SIZE.get(3), HOUSES_COLOR.get(3), HOUSES_ROOM.get(3)));
        houseDTOS.add(MockUtils.buildHouse(HOUSES_SIZE.get(4), HOUSES_COLOR.get(4), HOUSES_ROOM.get(4)));
    }

    @AfterAll
    public static void dropDB() {
        TableManager.dropDatabase(CITY_DATABASE);
    }

    @Test
    public void getBySizeTest() {
        try {
            houseDTOS.forEach(ThrowingConsumerWrapper.accept(d -> HOUSE_DAO.save(d, HouseDTO.class), SQLException.class));

            List<HouseDTO> whiteHouses = HOUSE_DAO.getByColor(HOUSES_COLOR.get(0));
            int whiteHousesExpectedSize = 2;
            List<HouseDTO> blackHouses = HOUSE_DAO.getByColor(HOUSES_COLOR.get(1));
            int blackHousesExpectedSize = 3;
            List<HouseDTO> greenHouses = HOUSE_DAO.getByColor(HOUSES_COLOR.get(2));
            int greenHousesExpectedSize = 1;
            List<HouseDTO> blackAndWhiteHouses = HOUSE_DAO.getByColor(HOUSES_COLOR.get(3));
            int blackAndWhiteHousesExpectedSize = 2;
            List<HouseDTO> redHouses = HOUSE_DAO.getByColor(HOUSES_COLOR.get(4));
            int redHousesExpectedSize = 1;


            assertNotNull(whiteHouses, "White houses expected list size: " + whiteHousesExpectedSize);
            assertNotNull(blackHouses, "Black houses expected list size: " + blackHousesExpectedSize);
            assertNotNull(greenHouses, "Green houses expected list size: " + greenHousesExpectedSize);
            assertNotNull(blackAndWhiteHouses, "Black and white houses expected list size: " + blackAndWhiteHousesExpectedSize);
            assertNotNull(redHouses, "Red houses expected list size: " + redHousesExpectedSize);
            assertEquals(whiteHousesExpectedSize, whiteHouses.size(), "White houses expected list size: " + whiteHousesExpectedSize
                    + ", found list size: " + whiteHouses.size());
            assertEquals(blackHousesExpectedSize, blackHouses.size(), "Black houses expected list size: " + blackHousesExpectedSize
                    + ", found list size: " + blackHouses.size());
            assertEquals(greenHousesExpectedSize, greenHouses.size(), "Green houses expected list size: " + greenHousesExpectedSize
                    + ", found list size: " + greenHouses.size());
            assertEquals(blackAndWhiteHousesExpectedSize, blackAndWhiteHouses.size(), "Black and white houses expected list size: "
                    + blackAndWhiteHousesExpectedSize + ", found list size: " + blackHouses.size());
            assertEquals(redHousesExpectedSize, redHouses.size(), "Red houses expected list size: " + redHousesExpectedSize
                    + ", found list size: " + redHouses.size());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
