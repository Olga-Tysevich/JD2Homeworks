package org.example.lesson9.dao.impl;

import org.example.lesson9.dao.PersonDAO;
import org.example.lesson9.dto.Person;

import javax.persistence.Query;

public class PersonDAOImpl extends DAOImpl<Person> implements PersonDAO {
    @Override
    public Person increaseAge(int id, int number) {
        startTransaction();
        Query query = getManager().createNamedQuery("increaseAge", Person.class);
        query.setParameter("number", number)
                .setParameter("id", id);
        query.executeUpdate();
        Person result = getManager().find(Person.class, id);
        commit();
        return result;
    }
}
