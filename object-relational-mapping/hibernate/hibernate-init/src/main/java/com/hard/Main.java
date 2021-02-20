package com.hard;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
//                .addResource("xml_entity.hbm.xml")
//                .addAnnotatedClass(AnnotatedEntity.class)
                .configure();

        SessionFactory sessionFactory = configuration
                .buildSessionFactory();

        sessionFactory.close();
    }
}
