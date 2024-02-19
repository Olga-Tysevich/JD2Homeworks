package org.example.lesson10b3.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class HibernateUtil {
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("lesson10_b3");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void close() {
        FACTORY.close();
    }
}
