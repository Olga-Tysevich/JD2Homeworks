package org.example.lesson9.dao;

import org.example.lesson9.dto.HouseDTO;

import java.util.List;

public interface HouseDAO extends DAO<HouseDTO> {
    List<HouseDTO> getByColor(String color);
}
