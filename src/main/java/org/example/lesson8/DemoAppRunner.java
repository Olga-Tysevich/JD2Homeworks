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
import java.lang.reflect.Array;
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
        houseDemo.runner(HOUSES_IN_FILE_PATH, HOUSES_OUT_FILE_PATH, HouseDTO.class, HOUSE_DAO, housesUniqueMethod, "белый");
    }


    private void runner(String inFilePath, String outFilePath, Class<T> clazz, DAO<T> DAO, Method method, Object... methodParameters) {
        try {
            List<T> DTOList = GSON_MANAGER.readDTOList(inFilePath, clazz);
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


            var result = method.invoke(DAO, methodParameters);
            System.out.println("Unique method: " + method.getName());
            System.out.println(result);

            GSON_MANAGER.writeDTOList(outFilePath, result);
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