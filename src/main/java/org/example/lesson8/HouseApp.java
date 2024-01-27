package org.example.lesson8;

import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.DAO;
import org.example.lesson8.dao.impl.DAOImpl;
import org.example.lesson8.dao.HouseDAO;
import org.example.lesson8.dao.impl.HouseDAOImpl;
import org.example.lesson8.dto.HouseDTO;
import org.example.lesson8.utils.DemoManager;
import org.example.lesson8.utils.GsonManager;
import org.example.lesson8.utils.TableManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.example.lesson8.utils.Constants.*;

public class HouseApp {
    private static final GsonManager GSON_MANAGER = new GsonManager();
    private static final HouseDAO HOUSE_DAO = new HouseDAOImpl();
    private static final DAO<HouseDTO> DAO = new DAOImpl<>();
    private static final DemoManager<HouseDTO> DEMO_MANAGER = new DemoManager<>();

    public static void main(String[] args) {
        try {
            DEMO_MANAGER.setDatabaseName(CITY_DATABASE);
            DEMO_MANAGER.setCreateTableQuery(CREATE_TABLE_HOUSES);
            DEMO_MANAGER.setDAO(DAO);
            List<HouseDTO> housesDTO = GSON_MANAGER.readHousesDTOList(HOUSES_IN_FILE_PATH);

            if (!housesDTO.isEmpty()) {
                DEMO_MANAGER.setDtoList(housesDTO);

                int randomId = RANDOM.nextInt(housesDTO.size());

                HouseDTO test = housesDTO.get(randomId);
                test.setId(1);
                List<HouseDTO> houseDTOList = DEMO_MANAGER.createDemo(HouseDTO.class, List.of(1, 2, 3, 4, 5), test);

                if (!houseDTOList.isEmpty()) {
                    List<HouseDTO> houses = HOUSE_DAO.getAll();
                    System.out.println("Get all:");
                    houses.forEach(System.out::println);

                    GSON_MANAGER.writeHousesDTOList(HOUSES_OUT_FILE_PATH, houseDTOList);
                }
            }

            TableManager.dropDatabase(CITY_DATABASE);

            SQLConnection.closeConnection();

        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
