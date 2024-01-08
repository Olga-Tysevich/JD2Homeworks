package org.example.lesson4.util;

import org.example.lesson4.models.Person;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class PersonsManager {
    public static List<Person> filterPersonsGroup(List<Person> personList, Predicate<Person> predicate) {
        return personList.stream()
                .filter(predicate)
                .toList();
    }

    public static List<Person> sortBySurnameAndName(List<Person> personList) {
        return  personList.stream()
                .sorted(Comparator.comparing(Person::getSurname)
                        .thenComparing(Person::getName))
                .toList();
    }

    public static List<Person> removeDuplicates(List<Person> personList) {
        return personList.stream()
                .distinct()
                .toList();
    }

    public static List<String> getSurnamesAndNames(List<Person> personList) {
        return personList.stream()
                .map(p ->  p.getSurname() + " " + p.getName())
                .toList();
    }
}
