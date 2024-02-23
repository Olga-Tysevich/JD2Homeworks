package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.AddressDAO;
import org.example.lesson9.dto.AddressDTO;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.function.Consumer;

import static org.example.lesson9.utils.Constants.*;

public class AddressDAOImpl extends DAOImpl<AddressDTO> implements AddressDAO {

    @Override
    public AddressDTO increaseHouseNumber(int id, int increment) {
        AddressDTO result = get(id);
        if (result != null) {
            EntityManager manager = getManager();
            Consumer<AddressDTO> increaseHouse = (o) -> {
                Query query = manager.createNativeQuery(CHANGE_HOUSE_NUMBER, AddressDTO.class);
                query.setParameter(INCREMENT, increment)
                        .setParameter(ID, id);
                query.executeUpdate();
                manager.refresh(result);
            };
            executeTransaction(increaseHouse, result);
            manager.refresh(result);
        }
        return result;
    }

    @Override
    protected Class<AddressDTO> getClazz() {
        return AddressDTO.class;
    }

}
