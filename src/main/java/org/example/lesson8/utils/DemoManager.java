package org.example.lesson8.utils;

import org.example.lesson8.dao.DAO;
import org.example.lesson8.utils.wrappers.ThrowingConsumerWrapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DemoManager<T> {
    private static final Random RANDOM = new Random();
    private String databaseName;
    private String createTableQuery;
    private DAO<T> DAO;
    private List<T> dtoList;

    public List<T> createDemo(Class<T> clazz, List<Integer> idList, T objectForUpdate) {
        try {
            TableManager.createDatabase(databaseName);
            TableManager.createTable(createTableQuery);
            System.out.println("List before save:");
            dtoList.forEach(System.out::println);
            dtoList.forEach(ThrowingConsumerWrapper.accept(dto -> DAO.save(dto, clazz), SQLException.class));

            List<T> dtoAfterSave = new ArrayList<>();

            idList.forEach(ThrowingConsumerWrapper.accept(id -> dtoAfterSave.add(DAO.get(id, clazz)), SQLException.class));
            if (!dtoAfterSave.isEmpty()) {
                System.out.println("\nList after save: ");
                dtoAfterSave.forEach(System.out::println);

                int randomId = idList.get(RANDOM.nextInt(idList.size()));

                T s = DAO.get(randomId, clazz);
                System.out.println("Get object by id: " + randomId + ": " + s);
                System.out.println("Updated rows: " + DAO.update(objectForUpdate));
                System.out.println("Deleted rows: " + DAO.delete(randomId, clazz));
            }

            return dtoAfterSave;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void setCreateTableQuery(String createTableQuery) {
        this.createTableQuery = createTableQuery;
    }

    public void setDAO(org.example.lesson8.dao.DAO<T> DAO) {
        this.DAO = DAO;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setDtoList(List<T> dtoList) {
        this.dtoList = dtoList;
    }
}
