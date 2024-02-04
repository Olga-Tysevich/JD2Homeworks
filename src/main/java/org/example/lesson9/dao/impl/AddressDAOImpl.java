package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.AddressDAO;
import org.example.lesson9.dto.Address;

import javax.persistence.Query;

public class AddressDAOImpl extends DAOImpl<Address> implements AddressDAO {
    @Override
    public Address increaseHouseNumber(int id, int number) {
        startTransaction();
        Query query = getManager().createNamedQuery("change_house_number", Address.class);
        query.setParameter("number", number)
                .setParameter("id", id);
        query.executeUpdate();
        Address result = getManager().find(Address.class, id);
        commit();
        return result;
    }
}
