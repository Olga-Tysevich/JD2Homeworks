package org.example.lesson8.utils.generators;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.lesson8.utils.Constants.*;

public class SQLQueryGenerator {
    private static final CustomBinaryOperator<Map<String, String>, String> namesTransformation = (m, c) -> c;
    private static final CustomBinaryOperator<Map<String, String>, String> valuesTransformation = Map::get;
    private static final CustomBinaryOperator<Map<String, String>, String> parameterTransformation = (m, c) -> c + EQUALS_SIGN + m.get(c);

    private static final StringBuilder currentQuery = new StringBuilder();

    public static String createInsert(String databaseName, String tableName, Map<String, String> columns) {
        cleanCurrentQuery();
        String fieldsNames = String.join(", ", getParametersAsString(namesTransformation, columns));
        String fieldsValues = String.join(", ", getParametersAsString(valuesTransformation, columns));
        createQuery(INSERT_QUERY_PATTERN, databaseName, tableName, fieldsNames, fieldsValues);
        return currentQuery.toString();
    }

    public static String createUpdate(String databaseName, String tableName, Map<String, String> primaryKeys, Map<String, String> columns) {
        cleanCurrentQuery();
        Map<String, String> temp = new LinkedHashMap<>();
        columns.keySet().forEach(k -> temp.put(parameterTransformation.apply(columns, k), columns.get(k)));
        String result = String.join(", ", getParametersAsString(namesTransformation, temp));
        String primaryKey = String.join(", ", getParametersAsString(parameterTransformation, primaryKeys));
        createQuery(UPDATE_QUERY_PATTERN, databaseName, tableName, result, primaryKey);
        return currentQuery.toString();
    }

    public static String createSelectOrDelete(String databaseName, String queryPattern, String tableName, String primaryKey, String primaryKeyValue) {
        cleanCurrentQuery();
        currentQuery.append(queryPattern);
        setParameterValue(databaseName + POINT_SIGN + tableName);
        setParameterValue(primaryKey + EQUALS_SIGN + primaryKeyValue);
        String t = currentQuery.toString();
        return currentQuery.toString();
    }

    private static void createQuery(String queryPattern, String databaseName, String tableName, String fieldsNames, String fieldsValues) {
        setTableName(queryPattern, databaseName, tableName);
        setParameterValue(fieldsNames);
        setParameterValue(fieldsValues);
    }

    private static void setTableName(String queryPattern, String databaseName, String tableName) {
        currentQuery.append(queryPattern.replaceFirst(PARAMETER_PATTERN, databaseName + POINT_SIGN + tableName));
    }

    private static void setParameterValue(String value) {
        currentQuery.replace(currentQuery.indexOf(PARAMETER_INDEX),
                currentQuery.indexOf(PARAMETER_INDEX) + PARAMETER_INDEX.length(),
                value);
    }

    private static List<String> getParametersAsString(CustomBinaryOperator<Map<String, String>, String> conversionType, Map<String, String> columns) {
        return columns.keySet().stream()
                .map(p -> conversionType.apply(columns, p))
                .collect(Collectors.toList());
    }

    private static void cleanCurrentQuery() {
        currentQuery.delete(0, currentQuery.length());
    }
}
