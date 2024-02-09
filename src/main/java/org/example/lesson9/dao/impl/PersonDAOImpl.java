package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.PersonDAO;
import org.example.lesson9.dto.Person;
import org.example.lesson9.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PersonDAOImpl extends DAOImpl<Person> implements PersonDAO {
    private final String increaseAge = "update people set age = age + :number where id = :id";

    @Override
    public Person increaseAge(int id, int number) {
        EntityManager manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery(increaseAge, Person.class);
        query.setParameter("number", number)
                .setParameter("id", id);
        query.executeUpdate();
        Person result = manager.find(Person.class, id);
        manager.getTransaction().commit();
        manager.close();
        return result;
    }

    @Override
    protected Class<Person> getClazz() {
        return Person.class;
    }
}
