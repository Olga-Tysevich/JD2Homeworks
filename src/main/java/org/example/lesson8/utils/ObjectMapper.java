package org.example.lesson8.utils;

import org.example.lesson8.utils.generators.SQLQueryGenerator;
import org.example.lesson8.utils.wrappers.ThrowingConsumerWrapper;
import java.lang.reflect.Field;
import java.util.*;

import static org.example.lesson8.utils.Constants.*;

public class ObjectMapper<T> {

    public String generateInsert(T object) {
        String databaseName = ReflectionManager.getDatabaseName(object.getClass());
        String tableName = ReflectionManager.getTableName(object.getClass());
        List<Field> nonGeneratedColumns = ReflectionManager.getNonGeneratedColumns(object.getClass());

        Map<String, String> columns = getFieldsForQuery(object, nonGeneratedColumns);

        if (nonGeneratedColumns.isEmpty()) {
            throw new IllegalArgumentException(FIELDS_ERROR);
        }
        return SQLQueryGenerator.createInsert(databaseName, tableName, columns);
    }

    public String generateUpdate(T object) {
        String databaseName = ReflectionManager.getDatabaseName(object.getClass());
        String tableName = ReflectionManager.getTableName(object.getClass());
        List<Field> nonGeneratedColumns = ReflectionManager.getNonGeneratedColumns(object.getClass());
        List<Field> keyList = ReflectionManager.getAllKeys(object.getClass());

        Map<String, String> columns = getFieldsForQuery(object, nonGeneratedColumns);
        Map<String, String> keys = getFieldsForQuery(object, keyList);

        if (nonGeneratedColumns.isEmpty() || keyList.size() != 1) {
            throw new IllegalArgumentException(columns.isEmpty() ? FIELDS_ERROR : PRIMARY_KEY_ERROR);
        }
        return SQLQueryGenerator.createUpdate(databaseName, tableName, keys, columns);
    }

    public String generateGet(int id, Class<T> clazz) {
        return createSelectOrDelete(SELECT_QUERY_PATTERN, id, clazz);
    }

    public String generateDelete(int id, Class<T> clazz) {
        return createSelectOrDelete(DELETE_QUERY_PATTERN, id, clazz);
    }

    private String createSelectOrDelete(String queryPattern, int id, Class<T> clazz) {
        List<Field> keys = ReflectionManager.getAllKeys(clazz);

        if (keys.size() == 1) {
            String databaseName = ReflectionManager.getDatabaseName(clazz);
            String tableName = ReflectionManager.getTableName(clazz);
            String primaryKeyName = ReflectionManager.getColumnName(keys.get(0));
            return SQLQueryGenerator.createSelectOrDelete(databaseName, queryPattern, tableName, primaryKeyName, String.valueOf(id));
        } else {
            throw new IllegalArgumentException(PRIMARY_KEY_ERROR);
        }
    }

    private Map<String, String> getFieldsForQuery(T object, List<Field> columns) {
        Map<String, String> result = new LinkedHashMap<>();
        columns.forEach(ThrowingConsumerWrapper.accept(
                c -> result.put(ReflectionManager.getColumnName(c), "'" + c.get(object) + "'"),
                IllegalAccessException.class));
        return result;
    }

}
