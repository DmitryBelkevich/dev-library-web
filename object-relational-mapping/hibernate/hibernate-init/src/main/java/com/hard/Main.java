package com.hard;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
//                .addResource("entity1.hbm.xml")
//                .addAnnotatedClass(Entity2.class)
                .configure();

        SessionFactory sessionFactory = configuration
                .buildSessionFactory();

        sessionFactory.close();
    }
}
