package org.example.lesson8.dao.impl;


import org.example.lesson8.dao.PersonDAO;
import org.example.lesson8.dto.Person;
import org.example.lesson8.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {
    private EntityManager manager;

    @Override
    public List<Person> getPersonsOverAge(int age) {
        startTransaction();
        TypedQuery<Person> query = manager.createNamedQuery("select_by_age", Person.class);
        query.setParameter("age", age);
        List<Person> result = query.getResultList();
        commit();
        return result;
    }

    @Override
    public List<Person> saveAll(List<Person> personList) {
        startTransaction();
        personList.forEach(p -> manager.persist(p));
        commit();
        return personList;
    }

    @Override
    public Person save(Person person) {
        startTransaction();
        manager.persist(person);
        commit();
        return person;
    }

    @Override
    public Person get(int id, Class<Person> clazz) {
        startTransaction();
        Person result = manager.find(clazz, id);
        commit();
        return result;
    }

    @Override
    public Person update(Person person) {
        startTransaction();
        manager.merge(person);
        commit();
        return person;
    }

    @Override
    public void delete(int id, Class<Person> clazz) {
        startTransaction();
        Person personForDelete = manager.find(Person.class, id);
        if (personForDelete != null) {
            manager.remove(personForDelete);
        }
        commit();
    }

    private void startTransaction() {
        manager = HibernateUtil.getEntityManager();
        manager.getTransaction().begin();
    }

    private void commit() {
        manager.getTransaction().commit();
        manager.close();
    }
}
