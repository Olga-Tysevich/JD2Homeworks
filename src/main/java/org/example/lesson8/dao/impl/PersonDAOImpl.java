package org.example.lesson8.dao.impl;


import org.example.lesson8.dao.PersonDAO;
import org.example.lesson8.dto.Person;
import org.example.lesson8.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {
    private EntityManager manager;

//    @Override
//    public List<Person> getPersonsOverAge(int age) {
//        startTransaction();
//        TypedQuery<Person> query = manager.createNamedQuery("select_by_age", Person.class);
//        query.setParameter("age", age);
//        List<Person> result = query.getResultList();
//        commit();
//        return result;
//    }

    @Override
    public List<Person> getPersonsOverAge(int age) {
        List<Person> result;
        Session session = HibernateUtil.getEntityManager().unwrap(Session.class);
        Transaction transaction = session.beginTransaction();
        session.enableFilter("overAge").setParameter("maxAge", age);
        result = session.createQuery("from Person", Person.class).getResultList();
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public List<Person> saveAll(List<Person> personList) {
        startTransaction();
        personList.stream()
                .filter(p -> p.getId() != 0)
                .forEach(p -> manager.merge(p));
        personList.stream().filter(p -> p.getId() == 0)
                .forEach(p -> manager.persist(p));
        commit();
        return personList;
    }

    @Override
    public Person save(Person person) {
        startTransaction();
        if (manager.find(Person.class, person.getId()) == null) {
            manager.persist(person);
        } else {
           manager.merge(person);
        }
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
