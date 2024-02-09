package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.AddressDAO;
import org.example.lesson9.dto.Address;
import org.example.lesson9.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AddressDAOImpl extends DAOImpl<Address> implements AddressDAO {
    private final String changeHouseNumber = "update addresses set house = house + :number where id = :id";

    @Override
    public Address increaseHouseNumber(int id, int number) {
        EntityManager manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery(changeHouseNumber, Address.class);
        query.setParameter("number", number)
                .setParameter("id", id);
        query.executeUpdate();
        Address result = manager.find(Address.class, id);
        manager.getTransaction().commit();
        manager.close();
        return result;
    }

    @Override
    protected Class<Address> getClazz() {
        return Address.class;
    }
}
