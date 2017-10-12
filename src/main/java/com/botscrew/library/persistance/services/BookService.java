package com.botscrew.library.persistance.services;

import com.botscrew.library.persistance.entities.Book;

import java.util.Comparator;
import java.util.List;

public interface BookService {

    void addBook(Book book);

    boolean removeBookByName(String bookName);

    void updateBook(Book book);

    List<Book> getBooksByName(String name);

    List<Book> getAllBooks();

    List<Book> getAllBooksOrdered(Comparator<Book> comparator);
}
