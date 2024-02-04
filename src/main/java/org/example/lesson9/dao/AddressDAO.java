package org.example.lesson9.dao;

import org.example.lesson9.dto.Address;

public interface AddressDAO extends DAO<Address> {
    Address increaseHouseNumber(int id, int number);
}
