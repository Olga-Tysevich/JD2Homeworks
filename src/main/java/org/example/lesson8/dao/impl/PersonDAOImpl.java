package org.example.lesson8.dao.impl;


import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.PersonDAO;
import org.example.lesson8.dto.PersonDTO;

import java.sql.*;

import static org.example.lesson8.utils.Constants.GET_BY_PASSPORT_PATTERN;

public class PersonDAOImpl extends DAOImpl<PersonDTO> implements PersonDAO {

    @Override
    public PersonDTO getByPassport(String passport) throws SQLException {
        try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(GET_BY_PASSPORT_PATTERN)) {
            statement.setString(1, passport);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return super.getMapper().get(resultSet, PersonDTO.class);
                }
                return null;
            }
        }
    }
}
