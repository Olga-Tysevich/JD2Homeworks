package org.example.lesson4;

import org.example.lesson4.models.Person;

import java.util.function.Predicate;

public class Constants {
    protected static final int GROUP_SIZE = 100;
    protected static final int MIN_AGE = 15;
    protected static final int MAX_AGE = 30;
    protected static final Predicate<Person> PERSON_UNDER_21 = p -> p.getAge() < 21;
    protected static final String FILE_PATH = "src/main/java/org/example/lesson4/Persons.txt";
}
