package org.example.lesson8;

import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.DAO;
import org.example.lesson8.dao.DoorDAO;
import org.example.lesson8.dao.impl.DAOImpl;
import org.example.lesson8.dao.impl.DoorDAOImpl;
import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.utils.DemoManager;
import org.example.lesson8.utils.GsonManager;
import org.example.lesson8.utils.TableManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.example.lesson8.utils.Constants.*;

public class DoorsApp {
    private static final GsonManager GSON_MANAGER = new GsonManager();
    private static final DoorDAO DOORS_DAO = new DoorDAOImpl();
    private static final DAO<DoorDTO> DAO = new DAOImpl<>();
    private static final DemoManager<DoorDTO> DEMO_MANAGER = new DemoManager<>();

    public static void main(String[] args) {
        try {
            DEMO_MANAGER.setDatabaseName(DOOR_DATABASE);
            DEMO_MANAGER.setCreateTableQuery(CREATE_TABLE_DOORS);
            DEMO_MANAGER.setDAO(DAO);
            List<DoorDTO> doorDTOList = GSON_MANAGER.readDoorsDTOList(DOORS_IN_FILE_PATH);

            if (doorDTOList != null &&!doorDTOList.isEmpty()) {

                DEMO_MANAGER.setDtoList(doorDTOList);

                int randomId = RANDOM.nextInt(doorDTOList.size());

                DoorDTO test = doorDTOList.get(randomId);
                test.setId(1);
                List<DoorDTO> doorDTOS = DEMO_MANAGER.createDemo(DoorDTO.class, List.of(1, 2, 3, 4, 5), test);

                List<DoorDTO> doors = DOORS_DAO.getBySize(900, 1300);
                if (doors != null && !doors.isEmpty()) {
                    System.out.println("Get by size from 900 to 1300: ");
                    doors.forEach(System.out::println);

                    GSON_MANAGER.writeDoorsDTOList(DOORS_OUT_FILE_PATH, doorDTOS);
                } else {
                    System.out.println("Sorry, nothing found");
                }
            }

            TableManager.dropDatabase(DOOR_DATABASE);

            SQLConnection.closeConnection();

        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
