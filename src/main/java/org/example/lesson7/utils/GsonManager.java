package org.example.lesson7.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.example.lesson7.dto.PersonDTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
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

    public void writePersonDTOToFile(String filePath, PersonDTO personDTO) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.write(convertToJson(personDTO));
        }
    }

    private String convertToJson(PersonDTO personDTO) {
        return gson.toJson(personDTO);
    }

    public PersonDTO readPersonFromJson(String filePath) throws IOException {
        String personDTOString = readAsString(filePath);
        return gson.fromJson(personDTOString, PersonDTO.class);
    }

    private String readAsString(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }

    public void writePersonDTOList(String filePath, List<PersonDTO> personDTOList) throws FileNotFoundException {
        String personsDTOList = gson.toJson(personDTOList);
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(personsDTOList);
        }
    }

    public List<PersonDTO> readPersonDTOList(String filePath) throws IOException {
        String personDTOList = readAsString(filePath);
        return gson.fromJson(personDTOList, new TypeToken<List<PersonDTO>>() {
        }.getType());
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


