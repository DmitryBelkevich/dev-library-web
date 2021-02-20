package com.hard;

import com.hard.models.Entity2;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();

        // Database connection settings
        properties.setProperty(Environment.DRIVER, "org.postgresql.Driver");
        properties.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/database1");
        properties.setProperty(Environment.USER, "postgres");
        properties.setProperty(Environment.PASS, "1234");

        // SQL dialect
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");

        // JDBC connection pool (use the built-in)
        properties.setProperty(Environment.POOL_SIZE, "1");

        // Echo all executed SQL to stdout
        properties.setProperty(Environment.SHOW_SQL, "true");

        // Enable Hibernate's automatic session context management
        properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        // Drop the existing tables and create new one
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");

        Configuration configuration = new Configuration()
                .setProperties(properties)
                .addResource("entity1.hbm.xml")
                .addAnnotatedClass(Entity2.class);

        SessionFactory sessionFactory = configuration
                .buildSessionFactory();

        sessionFactory.close();
    }
}
