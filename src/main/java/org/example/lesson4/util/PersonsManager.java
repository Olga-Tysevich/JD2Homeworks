package org.example.lesson4.util;

import org.example.lesson4.models.Person;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonsManager {
    public static List<Person> filterPersonsGroup(List<Person> personList, Predicate<Person> predicate) {
        return personList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static List<Person> sortBySurnameAndName(List<Person> personList) {
        return  personList.stream()
                .sorted(Comparator.comparing(Person::getSurname)
                        .thenComparing(Person::getName))
                .collect(Collectors.toList());
    }

    public static List<Person> removeDuplicates(List<Person> personList) {
        return personList.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<String> getSurnamesAndNames(List<Person> personList) {
        return personList.stream()
                .map(p ->  p.getSurname() + " " + p.getName())
                .collect(Collectors.toList());
    }
}
