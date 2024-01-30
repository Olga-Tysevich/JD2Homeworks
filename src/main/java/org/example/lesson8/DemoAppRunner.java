package org.example.lesson8;

import com.google.gson.reflect.TypeToken;
import org.example.lesson8.annotations.PrimaryKey;
import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.DAO;
import org.example.lesson8.dao.DoorDAO;
import org.example.lesson8.dao.HouseDAO;
import org.example.lesson8.dao.impl.DAOImpl;
import org.example.lesson8.dao.impl.DoorDAOImpl;
import org.example.lesson8.dao.impl.HouseDAOImpl;
import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.dto.HouseDTO;
import org.example.lesson8.utils.GsonManager;
import org.example.lesson8.utils.wrappers.ThrowingConsumerWrapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.lesson8.utils.Constants.*;

public class DemoAppRunner<T> {
    private static final GsonManager GSON_MANAGER = new GsonManager();
    private static final DoorDAO DOORS_DAO = new DoorDAOImpl();
    private static final HouseDAO HOUSE_DAO = new HouseDAOImpl();

    public static void main(String[] args) {
        DemoAppRunner<DoorDTO> doorDemo = new DemoAppRunner<>();
        DemoAppRunner<HouseDTO> houseDemo = new DemoAppRunner<>();
        doorDemo.runner(DOORS_IN_FILE_PATH, DoorDTO.class, DOORS_DAO);
        houseDemo.runner(HOUSES_IN_FILE_PATH, HouseDTO.class, HOUSE_DAO);

        try {
            List<DoorDTO> doors = DOORS_DAO.getBySize(900, 1300);
            List<HouseDTO> houses = HOUSE_DAO.getByColor("белый");
            if (houses != null && !houses.isEmpty()) {
                System.out.println("Get doors by size: ");
                doors.forEach(System.out::println);
                System.out.println("Get houses by color: ");
                houses.forEach(System.out::println);

                GSON_MANAGER.writeDTOList(DOORS_OUT_FILE_PATH, doors);
                GSON_MANAGER.writeDTOList(HOUSES_OUT_FILE_PATH, houses);
            } else {
                System.out.println("Sorry, nothing found");
            }
            SQLConnection.closeConnection();
        } catch (SQLException | FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }


    private void runner(String inFileName, Class<T> clazz, DAO<T> DAO) {
        try {
            List<T> DTOList = GSON_MANAGER.readDTOList(inFileName, clazz);
            System.out.println("List before save:");
            DTOList.forEach(System.out::println);

            List<T> dtoAfterSave = new ArrayList<>();
            DTOList.forEach(ThrowingConsumerWrapper.accept(dto -> dtoAfterSave.add(DAO.save(dto, clazz)), SQLException.class));

            if (!dtoAfterSave.isEmpty()) {
                System.out.println("\nList after save: ");
                dtoAfterSave.forEach(System.out::println);
            }

            int randomObject = RANDOM.nextInt(dtoAfterSave.size());
            Object id = getId(dtoAfterSave.get(randomObject));
            int randomObjectId = id != null ? (int) id : 1;

            T objectForUpdate = DAO.get(randomObjectId, clazz);

            System.out.println("Get object by id: " + randomObjectId + ": " + objectForUpdate);
            System.out.println("Updated rows: " + DAO.update(objectForUpdate));
            System.out.println("Deleted rows: " + DAO.delete(randomObjectId, clazz));

        } catch (IOException | SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }

    private Object getId(T object) {
        List<Field> idFields = Arrays.stream(object.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(PrimaryKey.class))
                .peek(f -> f.setAccessible(true))
                .collect(Collectors.toList());
        if (idFields.size() == 1) {
            try {
                return idFields.get(0).get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException(PRIMARY_KEY_ERROR);
        }
        return null;
    }
}