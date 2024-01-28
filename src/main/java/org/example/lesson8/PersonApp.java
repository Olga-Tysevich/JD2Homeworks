package org.example.lesson8;


import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.DAO;
import org.example.lesson8.dao.impl.DAOImpl;
import org.example.lesson8.dao.PersonDAO;
import org.example.lesson8.dao.impl.PersonDAOImpl;
import org.example.lesson8.utils.DemoManager;
import org.example.lesson8.utils.GsonManager;
import org.example.lesson8.dto.PersonDTO;
import org.example.lesson8.utils.TableManager;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.example.lesson8.utils.Constants.*;


public class PersonApp {
    private static final GsonManager GSON_MANAGER = new GsonManager();
    private static final PersonDAO PERSON_DAO = new PersonDAOImpl();
    private static final DAO<PersonDTO> DAO = new DAOImpl<>();
    private static final DemoManager<PersonDTO> DEMO_MANAGER = new DemoManager<>();

    public static void main(String[] args) {
        try {
            DEMO_MANAGER.setDatabaseName(PEOPLE_DATABASE);
            DEMO_MANAGER.setCreateTableQuery(CREATE_TABLE_PERSON);
            DEMO_MANAGER.setDAO(DAO);
            List<PersonDTO> personDTO = GSON_MANAGER.readPersonDTOList(PERSONS_IN_FILE_PATH);

            if (!personDTO.isEmpty()) {
                DEMO_MANAGER.setDtoList(personDTO);

                int randomId = RANDOM.nextInt(personDTO.size());

                PersonDTO test = personDTO.get(randomId);
                test.setId(1);

                List<PersonDTO> personDTOList = DEMO_MANAGER.createDemo(PersonDTO.class, List.of(1, 2, 3, 4, 5), test);
                System.out.println("Get by passport: " + test.getPassport() + ": " + PERSON_DAO.getByPassport(test.getPassport()));

                if (!personDTOList.isEmpty()) {
                    GSON_MANAGER.writePersonDTOList(PERSONS_OUT_FILE_PATH, personDTOList);
                }
            }

            TableManager.dropDatabase(PEOPLE_DATABASE);

            SQLConnection.closeConnection();
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
