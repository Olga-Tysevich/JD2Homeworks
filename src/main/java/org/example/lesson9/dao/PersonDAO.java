package org.example.lesson9.dao;

import org.example.lesson9.dto.Person;

public interface PersonDAO extends DAO<Person> {

    Person increaseAge(int id, int number);
}
