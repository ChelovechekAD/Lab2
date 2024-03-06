package org.unitasks.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("lab2Hibernate");


    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void closeFactory() {
        FACTORY.close();
    }

}
