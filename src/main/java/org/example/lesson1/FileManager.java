package org.example.lesson1;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FileManager {

    public ArrayList<StringContainer> readFile(String path, String columnSeparator) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> strings;
        File file = new File(path);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            while (reader.ready()) {
                strings = Arrays.stream(reader.readLine().split(columnSeparator)).collect(Collectors.toCollection(ArrayList::new));
                data.add(strings);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<StringContainer> result = new ArrayList<>();
        data.forEach(s -> result.add(new StringContainer(s)));
        return result;
    }

    public void writeFile(String path, ArrayList<StringContainer> data) {
        File file = new File(path);

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            data.forEach(sc -> writer.println(sc.toString()));
        } catch (IOException e) {
            throw new InvalidPathException(path, "Invalid path");
        }
    }

}
