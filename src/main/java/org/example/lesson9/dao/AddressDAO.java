package org.example.lesson9.dao;

import org.example.lesson9.dto.AddressDTO;

public interface AddressDAO extends DAO<AddressDTO> {
    AddressDTO increaseHouseNumber(int id, int increment);
}
