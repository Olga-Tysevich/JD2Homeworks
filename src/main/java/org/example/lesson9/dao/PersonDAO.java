package org.example.lesson9.dao;

import org.example.lesson9.dto.PersonDTO;

public interface PersonDAO extends DAO<PersonDTO> {

    PersonDTO increaseAge(int id, int increment);
}
