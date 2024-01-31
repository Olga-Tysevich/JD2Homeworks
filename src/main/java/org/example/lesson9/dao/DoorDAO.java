package org.example.lesson9.dao;

import org.example.lesson9.dto.DoorDTO;

import java.util.List;

public interface DoorDAO extends DAO<DoorDTO> {
    List<DoorDTO> getBySize(double fromSize, double toSize);
}
