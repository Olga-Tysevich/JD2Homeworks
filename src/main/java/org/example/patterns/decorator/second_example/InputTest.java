package org.example.patterns.decorator.second_example;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputTest {
    private static final String IN_FILE_PATH = "src\\main\\java\\org\\example\\patterns\\decorator\\example_from_book\\second_example\\test.txt";

    public static void main(String[] args) {
        int c;
        try (InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream(IN_FILE_PATH)))) {
            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
