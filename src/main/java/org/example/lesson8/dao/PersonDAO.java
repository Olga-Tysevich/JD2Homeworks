package org.example.lesson8.dao;


import org.example.lesson8.dto.PersonDTO;

import java.sql.SQLException;

public interface PersonDAO extends DAO<PersonDTO> {
    PersonDTO getByPassport(String passport) throws SQLException;
}
