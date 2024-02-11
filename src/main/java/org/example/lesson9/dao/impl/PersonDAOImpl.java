package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.PersonDAO;
import org.example.lesson9.dto.PersonDTO;
import org.example.lesson9.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.example.lesson9.utils.Constants.*;

public class PersonDAOImpl extends DAOImpl<PersonDTO> implements PersonDAO {

    @Override
    public PersonDTO increaseAge(int id, int increment) {
        EntityManager manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery(INCREASE_AGE, PersonDTO.class);
        query.setParameter(INCREMENT, increment)
                .setParameter(ID, id);
        query.executeUpdate();
        PersonDTO result = manager.find(PersonDTO.class, id);
        manager.getTransaction().commit();
        manager.close();
        return result;
    }

    @Override
    protected Class<PersonDTO> getClazz() {
        return PersonDTO.class;
    }
}
