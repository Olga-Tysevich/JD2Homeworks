package org.example.lesson4.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static <T extends Serializable> void writeObjects (List<T> objects, String outFilePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(outFilePath))) {
            objects.forEach(o -> {
                try {
                    outputStream.writeObject(o);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static List<Object> readObjects(String inFilePath) {
        List<Object> result = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(inFilePath))) {
            while (true) {
                try {
                    result.add(inputStream.readObject());
                } catch (ClassNotFoundException | EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
