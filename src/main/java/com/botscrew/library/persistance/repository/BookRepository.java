package com.botscrew.library.persistance.repository;

import com.botscrew.library.persistance.entities.Book;
import com.botscrew.library.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class BookRepository {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static void add(Book book) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.saveOrUpdate(book);

        session.getTransaction().commit();

    }

    public static boolean remove(String bookName) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();


        Query deleteQuery = session.createQuery("delete from Book where name = :name");
        deleteQuery.setParameter("name", bookName);

        int numberDeletedEntities = deleteQuery.executeUpdate();

        session.getTransaction().commit();

        return numberDeletedEntities > 0;
    }

    public static void update(Book book) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(book);

        session.getTransaction().commit();
    }

    public static List<Book> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Book> books = session.createQuery("from Book").list();

        session.getTransaction().commit();

        return books;
    }

    public static Book getById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Book book = session.find(Book.class, id);

        return book;
    }

}
