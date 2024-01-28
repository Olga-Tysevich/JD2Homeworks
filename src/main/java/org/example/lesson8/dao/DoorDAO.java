package org.example.lesson8.dao;

import org.example.lesson8.dto.DoorDTO;

import java.sql.SQLException;
import java.util.List;

public interface DoorDAO extends DAO<DoorDTO> {
    List<DoorDTO> getBySize(double fromSize, double toSize) throws SQLException;
}
