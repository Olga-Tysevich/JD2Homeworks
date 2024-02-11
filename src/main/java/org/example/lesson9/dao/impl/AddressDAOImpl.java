package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.AddressDAO;
import org.example.lesson9.dto.AddressDTO;
import org.example.lesson9.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.example.lesson9.utils.Constants.*;

public class AddressDAOImpl extends DAOImpl<AddressDTO> implements AddressDAO {

    @Override
    public AddressDTO increaseHouseNumber(int id, int increment) {
        EntityManager manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery(CHANGE_HOUSE_NUMBER, AddressDTO.class);
        query.setParameter(INCREMENT, increment)
                .setParameter(ID, id);
        query.executeUpdate();
        AddressDTO result = manager.find(AddressDTO.class, id);
        manager.getTransaction().commit();
        manager.close();
        return result;
    }

    @Override
    protected Class<AddressDTO> getClazz() {
        return AddressDTO.class;
    }
}
