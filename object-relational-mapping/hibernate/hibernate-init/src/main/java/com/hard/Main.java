package com.hard;

import com.hard.models.AnnotatedEntity;
import com.hard.models.XmlEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    private static AnnotatedEntity annotatedEntity = new AnnotatedEntity();
    private static XmlEntity xmlEntity = new XmlEntity();

    static {
        annotatedEntity.setTitle("entity 1");
        xmlEntity.setTitle("entity 1");
    }

    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .configure();

        SessionFactory sessionFactory = configuration
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(annotatedEntity);
        session.save(xmlEntity);

        transaction.commit();

        session.close();
        sessionFactory.close();
    }
}
