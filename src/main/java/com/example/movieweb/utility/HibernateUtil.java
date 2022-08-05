package com.example.movieweb.utility;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory factory;
    private static EntityManager entityManager;
    private static Session session;

    private static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null || factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory("jpa-example");
        }
        return factory;
    }

    private static EntityManager getEntityManager() {
        if (entityManager == null || entityManager.isOpen()) {
            entityManager = getEntityManagerFactory().createEntityManager();
        }
        return entityManager;
    }

    public static Session getSession() {
        if (session == null || !session.isOpen()) {
            session = getEntityManager().unwrap(Session.class);
        }
        return session;
    }

    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
            factory = null;
        }
    }
}
