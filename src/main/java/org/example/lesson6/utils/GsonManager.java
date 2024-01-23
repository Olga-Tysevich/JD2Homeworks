package org.example.lesson6.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.example.lesson6.dto.PersonDTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.List;

public class GsonManager {
    private static Gson gson;

    static {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Time.class, new TimeDeserializer())
                .create();
    }

    private GsonManager() {
    }

    private static class TimeDeserializer implements JsonDeserializer<Time> {
        @Override
        public Time deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return Time.valueOf(jsonElement.getAsString());
        }
    }

    public static void writeAllPersonDTO(String filePath, List<PersonDTO> personDTOList) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(gson.toJson(personDTOList));
        }
    }

    public static List<PersonDTO> readAllPersonDTO(String filePath) throws IOException {
        return gson.fromJson(
                readAsString(filePath),
                new TypeToken<List<PersonDTO>>() {
                }.getType());
    }

    private static String readAsString(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }
}
