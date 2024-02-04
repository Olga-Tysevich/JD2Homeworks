package org.example.lesson9.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class JsonManager {
    private static final Gson gson;

    static {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public static <T> List<T> readDTOList(String inFilePath, Class<T> dtoClass) throws IOException {
        String dtoList = readAsString(inFilePath);
        return gson.fromJson(dtoList, getTypeToken(dtoClass));
    }

    private static String readAsString(String inFilePath) throws IOException {
        return Files.readString(Paths.get(inFilePath));
    }

    private static Type getTypeToken(Class<?> clazz) {
        return TypeToken.getParameterized(ArrayList.class, clazz).getType();
    }
}
