package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.AddressDAO;
import org.example.lesson9.dto.AddressDTO;

import javax.persistence.Query;

import java.util.function.Supplier;

import static org.example.lesson9.utils.Constants.*;

public class AddressDAOImpl extends DAOImpl<AddressDTO> implements AddressDAO {

    @Override
    public AddressDTO increaseHouseNumber(int id, int increment) {
        Supplier<AddressDTO> increaseHouse = () -> {
            Query query = createNativeQuery(CHANGE_HOUSE_NUMBER, AddressDTO.class);
            query.setParameter(INCREMENT, increment)
                    .setParameter(ID, id);
            query.executeUpdate();
            AddressDTO result = get(id);
            refreshObject(result);
            return result;
        };
        return executeTransaction(increaseHouse);
    }

    @Override
    protected Class<AddressDTO> getClazz() {
        return AddressDTO.class;
    }

}
