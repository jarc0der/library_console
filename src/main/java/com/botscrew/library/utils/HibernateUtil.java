package com.botscrew.library.utils;

import com.botscrew.library.persistance.entities.Author;
import com.botscrew.library.persistance.entities.Book;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure()
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Author.class)
                    .setProperty("hibernate.show_sql", "false")
                    .buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
