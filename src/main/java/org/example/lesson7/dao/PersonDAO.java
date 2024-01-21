package org.example.lesson7.dao;


import org.example.lesson7.dto.PersonDTO;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO extends DAO<PersonDTO> {
    List<PersonDTO> getAll() throws SQLException;
}
