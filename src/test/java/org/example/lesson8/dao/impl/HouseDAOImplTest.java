package org.example.lesson8.dao.impl;


import org.example.lesson8.dao.HouseDAO;
import org.example.lesson8.dto.HouseDTO;
import org.example.lesson8.utils.wrappers.ThrowingConsumerWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.lesson8.dao.impl.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class HouseDAOImplTest {
    private static final HouseDAO HOUSE_DAO = new HouseDAOImpl();
    private static final List<HouseDTO> houseDTOS = new ArrayList<>();

    @BeforeAll
    public static void createHouseList() {

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

    @Test
    public void getBySizeTest() {
        try {
            MockUtils.createDatabase(DATABASE);
            MockUtils.createTable(CREATE_TABLE_HOUSES);
            List<HouseDTO> forDelete = new ArrayList<>();
            houseDTOS.forEach(ThrowingConsumerWrapper.accept(d -> forDelete.add(HOUSE_DAO.save(d, HouseDTO.class)), SQLException.class));

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

            forDelete.forEach(this::deleteTestHouse);
            assertTrue(whiteHouses.size() >= whiteHousesExpectedSize, "White houses expected list size: " + whiteHousesExpectedSize);
            assertTrue(blackHouses.size() >= blackHousesExpectedSize, "Black houses expected list size: " + blackHousesExpectedSize);
            assertTrue(greenHouses.size() >= greenHousesExpectedSize, "Green houses expected list size: " + greenHousesExpectedSize);
            assertTrue(blackAndWhiteHouses.size() >= blackAndWhiteHousesExpectedSize, "Black and white houses expected list size: "
                    + blackAndWhiteHousesExpectedSize);
            assertTrue(redHouses.size() >= redHousesExpectedSize, "Red houses expected list size: " + redHousesExpectedSize);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void deleteTestHouse(HouseDTO houseDTO)  {

        Object idForDelete = MockUtils.getId(houseDTO);
        if (idForDelete != null) {
            try {
                HOUSE_DAO.delete((int) idForDelete, HouseDTO.class);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
