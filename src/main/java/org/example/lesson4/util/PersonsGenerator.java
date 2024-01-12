package org.example.lesson4.util;

import org.example.lesson4.models.Person;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonsGenerator {
    private int minAge;
    private int maxAge;

    public PersonsGenerator(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public  List<Person> generatePersonsGroup(int groupSize) {
        return Stream.generate(this::generatePerson)
                .limit(groupSize)
                .collect(Collectors.toList());
    }

    private Person generatePerson() {
        String randomName = Names.values()[new Random().nextInt(Names.values().length)].name();
        String randomSurname = Surnames.values()[new Random().nextInt(Surnames.values().length)].name();
        int randomAge = new Random().nextInt(maxAge - minAge) + minAge;
        return new Person(randomName, randomSurname, randomAge);
    }
}
