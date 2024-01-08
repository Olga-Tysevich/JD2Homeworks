package org.example.lesson4;

import org.example.lesson4.models.Person;
import org.example.lesson4.util.FileManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileManagerTest {
    private static final String OBJECTS_FILE_PATH = "src/test/java/org/example/lesson4/objects.txt";
    private static final String FILE_PATH = "src/test/java/org/example/lesson4/test.txt";
    private static final Person PERSON_1 = new Person("TIMMY", "EVANS", 17);
    private static final Person PERSON_2 = new Person("LUCI", "WALKER", 19);


    @BeforeAll
    public static void writeObjects() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OBJECTS_FILE_PATH))) {
            oos.writeObject(PERSON_1);
            oos.writeObject(PERSON_2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fileManagerTest() {
        List<Person> expected = new ArrayList<>(List.of(PERSON_1, PERSON_2));
        FileManager.writeObjects(expected, FILE_PATH);

        long isFilesTheSame = 0;
        try {
            isFilesTheSame =  Files.mismatch(Path.of(OBJECTS_FILE_PATH), Path.of(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Person> actual = FileManager.readObjects(FILE_PATH).stream().map(p -> (Person) p).toList();
        assertTrue(new File(FILE_PATH).exists());
        assertEquals(-1L, isFilesTheSame);
        assertEquals(expected, actual);
    }

    @AfterAll
    public static void deleteFiles() {
        new File(OBJECTS_FILE_PATH).delete();
        new File(FILE_PATH).delete();
    }
}
