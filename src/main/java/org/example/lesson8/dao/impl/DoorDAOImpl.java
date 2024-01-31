package org.example.lesson8.dao.impl;

import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.DoorDAO;
import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.dto.HouseDTO;
import org.example.lesson8.utils.ResultSetHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.lesson8.utils.Constants.GET_DOORS_BY_SIZE_PATTERN;

public class DoorDAOImpl extends DAOImpl<DoorDTO> implements DoorDAO {
    @Override
    public List<DoorDTO> getBySize(double fromSize, double toSize) throws SQLException {
        List<DoorDTO> doorDTOList = new ArrayList<>();
        try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(GET_DOORS_BY_SIZE_PATTERN)) {
            statement.setDouble(1, fromSize);
            statement.setDouble(2, toSize);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    doorDTOList.add(ResultSetHandler.getObject(resultSet, DoorDTO.class));
                }
            }
        }
        return doorDTOList.isEmpty() ? null : doorDTOList;
    }
}
