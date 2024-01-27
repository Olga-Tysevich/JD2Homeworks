package org.example.lesson8.dao.impl;

import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.HouseDAO;
import org.example.lesson8.dto.HouseDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.example.lesson8.utils.Constants.GET_ALL_HOUSES_PATTERN;

public class HouseDAOImpl extends DAOImpl<HouseDTO> implements HouseDAO {

    @Override
    public List<HouseDTO> getAll() throws SQLException {
        List<HouseDTO> houseDTOList = new ArrayList<>();
        try (Statement statement = SQLConnection.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(GET_ALL_HOUSES_PATTERN)) {
                while (resultSet.next()) {
                    houseDTOList.add(super.getMapper().get(resultSet, HouseDTO.class));
                }
            }
        }
        return houseDTOList.isEmpty() ? null : houseDTOList;
    }
}
