package org.example.lesson4;

import org.example.lesson4.models.Person;
import org.example.lesson4.util.PersonsGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonsGeneratorTest {
    @ParameterizedTest
    @MethodSource("personsGroupCases")
    public void generatePersonsGroupTest(int minAge, int maxAge, int groupSize) {
        List<Person> actual = new PersonsGenerator(minAge, maxAge).generatePersonsGroup(groupSize);

        actual.forEach(p ->
                assertTrue(p.getAge() >= minAge && p.getAge() <= maxAge,
                        "Error on case: " + p + ", position: " + actual.indexOf(p)));
        assertEquals(groupSize, actual.size());
    }

    static Stream<Arguments> personsGroupCases() {
        return Stream.of(
                Arguments.of(15, 30, 100),
                Arguments.of(10, 25, 10),
                Arguments.of(55, 100, 25),
                Arguments.of(27, 108, 80),
                Arguments.of(10, 15, 79)
        );
    }

}
