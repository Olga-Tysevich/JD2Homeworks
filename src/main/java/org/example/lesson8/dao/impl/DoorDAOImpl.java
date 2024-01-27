package org.example.lesson8.dao.impl;

import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.DoorDAO;
import org.example.lesson8.dto.DoorDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.example.lesson8.utils.Constants.GET_ALL_DOORS_PATTERN;

public class DoorDAOImpl extends DAOImpl<DoorDTO> implements DoorDAO {
    @Override
    public List<DoorDTO> getAll() throws SQLException {
        List<DoorDTO> doorDTOList = new ArrayList<>();
        try (Statement statement = SQLConnection.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(GET_ALL_DOORS_PATTERN)) {
                while (resultSet.next()) {
                    doorDTOList.add(super.getMapper().get(resultSet, DoorDTO.class));
                }
            }
        }
        return doorDTOList.isEmpty() ? null : doorDTOList;
    }
}
