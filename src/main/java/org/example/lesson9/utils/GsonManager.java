package org.example.lesson9.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class GsonManager {
    private Gson gson;


    public GsonManager() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Time.class, new TimeDeserializer())
                .setDateFormat("yyyy-DD-MM")
                .create();
    }

    public void writeDTOList(String filePath, Object dtoList) throws FileNotFoundException {
        String doorsDTOList = gson.toJson(dtoList);
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(doorsDTOList);
        }
    }

    public<T> List<T> readDTOList(String filePath, Class<T> dtoClass) throws IOException {
        String DTOList = readAsString(filePath);
        return gson.fromJson(DTOList, setModelAndGetCorrespondingList(dtoClass));
    }

    private Type setModelAndGetCorrespondingList(Class<?> typeArgument) {
        return TypeToken.getParameterized(ArrayList.class, typeArgument).getType();
    }

    private String readAsString(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }

    private static class TimeDeserializer implements JsonDeserializer<Time> {

        @Override
        public Time deserialize(JsonElement jsonElement, Type typeOF,
                                JsonDeserializationContext context) throws JsonParseException {
            String time = jsonElement.getAsString();
            return Time.valueOf(time);
        }
    }
}
