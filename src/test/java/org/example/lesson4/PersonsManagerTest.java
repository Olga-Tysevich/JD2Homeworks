package org.example.lesson4;

import org.example.lesson4.models.Person;
import org.example.lesson4.util.PersonsManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonsManagerTest {
    private static final String NAME_1 = "SMITH DEMI";
    private static final String NAME_2 = "JONES MINDY";
    private static final String NAME_3 = "JONES DEMI";
    private static final Person PERSON_1 = new Person("DEMI", "SMITH", 18);
    private static final Person PERSON_2 = new Person("MINDY", "JONES", 20);
    private static final Person PERSON_3 = new Person("DEMI", "SMITH", 18);
    private static final Person PERSON_4 = new Person("DEMI", "JONES", 18);
    private static final Person PERSON_5 = new Person("DEMI", "SMITH", 25);
    private static final List<Person> personList = new ArrayList<>();

    @BeforeAll
    public static void createPersonList() {
        personList.add(PERSON_1);
        personList.add(PERSON_2);
        personList.add(PERSON_3);
        personList.add(PERSON_4);
        personList.add(PERSON_5);
    }

    @Test
    public void filterPersonsGroupTest() {
        final Predicate<Person> personUnder21 = p -> p.getAge() < 21;
        List<Person> expected = new ArrayList<>(List.of(PERSON_4, PERSON_2, PERSON_1));
        List<Person> actual = PersonsManager.filterPersonsGroup(personList, personUnder21);

        assertEquals(expected, actual);
    }


    @Test
    public void getSurnamesAndNamesTest() {
        List<String> expected = new ArrayList<>(List.of(NAME_1, NAME_2, NAME_1, NAME_3, NAME_1));
        List<String> actual = PersonsManager.getSurnamesAndNames(personList);

        assertEquals(expected, actual);
    }
}
