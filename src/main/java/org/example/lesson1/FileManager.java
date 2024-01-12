package org.example.lesson1;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.lesson1.Constants.ERROR_MESSAGE;

public class FileManager {

    public static List<DataComparator> readFile(String inFilePath, String columnSeparator) {
        List<DataComparator> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inFilePath))) {
            while (reader.ready()) {
                data.add(new DataComparator(
                        Arrays.stream(reader.readLine()
                                        .split(columnSeparator))
                                .collect(Collectors.toCollection(ArrayList::new))));
            }
        } catch (IOException e) {
            throw new InvalidPathException(inFilePath, ERROR_MESSAGE);
        }
        return data;
    }

    public static void writeFile(String outFilePath, List<DataComparator> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outFilePath))) {
            data.forEach(sc -> writer.println(sc.toString()));
        } catch (IOException e) {
            throw new InvalidPathException(outFilePath, ERROR_MESSAGE);
        }
    }

}
