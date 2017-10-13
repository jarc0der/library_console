package com.botscrew.library.persistance.repository;

import com.botscrew.library.persistance.entities.Author;
import com.botscrew.library.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AuthorRepository {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void add(Author author) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(author);

        session.getTransaction().commit();

    }


    public Author getLast() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //TODO: simplify this
        List<Author> authorList = session.createQuery("from Author").list();

        session.getTransaction().commit();

        return authorList.get(authorList.size() - 1);

    }

    public List<Author> getAll() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        List<Author> authorList = session.createQuery("from Author").list();

        session.getTransaction().commit();

        return authorList;
    }
}
