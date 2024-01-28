package org.example.lesson8.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.dto.HouseDTO;
import org.example.lesson8.dto.PersonDTO;

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

    public void writePersonDTOList(String filePath, List<PersonDTO> personDTOList) throws FileNotFoundException {
        String personsDTOList = gson.toJson(personDTOList);
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(personsDTOList);
        }
    }

    public void writeHousesDTOList(String filePath, List<HouseDTO> houseDTOList) throws FileNotFoundException {
        String houses = gson.toJson(houseDTOList);
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(houses);
        }
    }

    public void writeDoorsDTOList(String filePath, List<DoorDTO> doorDTOList) throws FileNotFoundException {
        String doorsDTOList = gson.toJson(doorDTOList);
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(doorsDTOList);
        }
    }

    public List<PersonDTO> readPersonDTOList(String filePath) throws IOException {
        String personDTOList = readAsString(filePath);
        return gson.fromJson(personDTOList, new TypeToken<List<PersonDTO>>() {
        }.getType());
    }

    public List<HouseDTO> readHousesDTOList(String filePath) throws IOException {
        String housesDTOList = readAsString(filePath);
        return gson.fromJson(housesDTOList, new TypeToken<List<HouseDTO>>() {
        }.getType());
    }

    public List<DoorDTO> readDoorsDTOList(String filePath) throws IOException {
        String doorsDTOList = readAsString(filePath);
        return gson.fromJson(doorsDTOList, new TypeToken<List<DoorDTO>>() {
        }.getType());
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
