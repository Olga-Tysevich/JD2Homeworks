package org.example.lesson8.dao;

import org.example.lesson8.dto.Person;

import java.util.List;

public interface PersonDAO extends DAO<Person> {
    List<Person> getPersonsOverAge(int age);
    List<Person> saveAll(List<Person> personList);
}
