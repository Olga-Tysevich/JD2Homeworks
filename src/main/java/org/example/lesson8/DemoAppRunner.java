package org.example.lesson8;

import org.example.lesson8.annotations.PrimaryKey;
import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.DAO;
import org.example.lesson8.dao.DoorDAO;
import org.example.lesson8.dao.HouseDAO;
import org.example.lesson8.dao.impl.DoorDAOImpl;
import org.example.lesson8.dao.impl.HouseDAOImpl;
import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.dto.HouseDTO;
import org.example.lesson8.utils.GsonManager;
import org.example.lesson8.utils.wrappers.ThrowingConsumerWrapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

    public static void main(String[] args) throws NoSuchMethodException {
        DemoAppRunner<DoorDTO> doorDemo = new DemoAppRunner<>();
        DemoAppRunner<HouseDTO> houseDemo = new DemoAppRunner<>();
        Method doorsUniqueMethod = DoorDAO.class.getDeclaredMethod("getBySize", double.class, double.class);
        Method housesUniqueMethod = HouseDAO.class.getDeclaredMethod("getByColor", String.class);
        doorDemo.runner(DOORS_IN_FILE_PATH, DOORS_OUT_FILE_PATH, DoorDTO.class, DOORS_DAO, doorsUniqueMethod, 900D, 1300D);
        houseDemo.runner(HOUSES_IN_FILE_PATH, HOUSES_OUT_FILE_PATH, HouseDTO.class, HOUSE_DAO, housesUniqueMethod, "черный");
    }


    private void runner(String inFilePath, String outFilePath, Class<T> clazz, DAO<T> dao, Method method, Object... methodParameters) {
        try {
            List<T> dtoList = GSON_MANAGER.readDTOList(inFilePath, clazz);
            System.out.println("List before save:");
            dtoList.forEach(System.out::println);

            List<T> dtoAfterSave = new ArrayList<>();
            dtoList.forEach(ThrowingConsumerWrapper.accept(dto -> dtoAfterSave.add(dao.save(dto, clazz)), SQLException.class));

            if (!dtoAfterSave.isEmpty()) {
                System.out.println("\nList after save: ");
                dtoAfterSave.forEach(System.out::println);
            }

            int randomObject = RANDOM.nextInt(dtoAfterSave.size());
            Object id = getId(dtoAfterSave.get(randomObject));
            int randomObjectId = id != null ? (int) id : 1;

            T objectForUpdate = dao.get(randomObjectId, clazz);

            System.out.println("Get object by id " + randomObjectId + ": " + objectForUpdate);
            System.out.println("Updated rows: " + dao.update(objectForUpdate));
            System.out.println("Deleted rows: " + dao.delete(randomObjectId, clazz));

            if (method.getParameterCount() != 0) {
                var result = method.invoke(dao, methodParameters);
                System.out.println("Unique method: " + method.getName() + "(" + Arrays.toString(methodParameters) + ")\n");
                if (result != null) {
                    System.out.println(result);
                } else {
                    System.out.println("Sorry, nothing found...\n");
                }
                GSON_MANAGER.writeDTOList(outFilePath, result);
            }

            SQLConnection.closeConnection();
        } catch (InvocationTargetException | IOException | SQLException | IllegalAccessException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
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