package org.example.lesson10b2.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class HibernateUtil {
    public static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("lesson10_b2");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void close() {
        FACTORY.close();
    }
}
