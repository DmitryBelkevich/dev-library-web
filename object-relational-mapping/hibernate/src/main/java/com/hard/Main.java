package com.hard;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .configure();

        SessionFactory sessionFactory = configuration
                .buildSessionFactory();

        sessionFactory.close();
    }
}
