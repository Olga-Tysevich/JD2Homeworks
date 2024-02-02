package org.example.lesson8.utils;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.example.lesson8.dto.Person;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonManager {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Person> readPersons(String inFilePath) {
        List<Person> personList = null;
        try {
            personList = mapper.readValue(new File(inFilePath), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public static void writePersons(List<Person> persons, String outFilePath) {
        try  {
            mapper.writeValue(new File(outFilePath), persons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
