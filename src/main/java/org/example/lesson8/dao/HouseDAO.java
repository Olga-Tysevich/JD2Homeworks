package org.example.lesson8.dao;

import org.example.lesson8.dto.HouseDTO;

import java.sql.SQLException;
import java.util.List;

public interface HouseDAO extends DAO<HouseDTO> {
    List<HouseDTO> getByColor(String color) throws SQLException;
}
