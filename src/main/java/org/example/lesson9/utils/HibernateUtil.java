package org.example.lesson9.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class HibernateUtil {
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("people");

    private HibernateUtil() {
    }

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void close() {
        FACTORY.close();
    }

    public static EntityManagerFactory getFactory() {
        return FACTORY;
    }
}
