package org.example.lesson8.dao.impl;

import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.HouseDAO;
import org.example.lesson8.dto.HouseDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.lesson8.utils.Constants.GET_HOUSES_BY_COLOR_PATTERN;

public class HouseDAOImpl extends DAOImpl<HouseDTO> implements HouseDAO {

    @Override
    public List<HouseDTO> getByColor(String color) throws SQLException {
        List<HouseDTO> houseDTOList = new ArrayList<>();
        try (PreparedStatement statement = SQLConnection.getConnection().prepareStatement(GET_HOUSES_BY_COLOR_PATTERN)) {
            statement.setString(1, color);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    houseDTOList.add(super.getMapper().get(resultSet, HouseDTO.class));
                }
            }
        }
        return houseDTOList.isEmpty() ? null : houseDTOList;
    }
}
