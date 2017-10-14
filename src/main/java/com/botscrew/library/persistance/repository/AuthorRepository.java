package com.botscrew.library.persistance.repository;

import com.botscrew.library.persistance.entities.Author;
import com.botscrew.library.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class AuthorRepository {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void add(Author author) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(author);

        session.getTransaction().commit();

    }

    public List<Author> getAll() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        List<Author> authorList = session.createQuery("from Author").list();

        session.getTransaction().commit();

        return authorList;
    }
}
