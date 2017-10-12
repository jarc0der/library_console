package com.botscrew.library.persistance.services;

import com.botscrew.library.persistance.entities.Book;
import com.botscrew.library.persistance.repository.BookRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    @Override
    public void addBook(Book book) {
        BookRepository.add(book);
    }

    @Override
    public boolean removeBookByName(String bookName) {
        return BookRepository.remove(bookName);
    }

    @Override
    public void updateBook(Book book) {
        //we can identify book by id
        BookRepository.update(book);
    }

    @Override
    public List<Book> getBooksByName(String name) {
        return getAllBooks().stream().filter(b -> b.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooks() {
        return BookRepository.getAll();
    }

    @Override
    public List<Book> getAllBooksOrdered(Comparator<Book> comparator) {
        return getAllBooks().stream().sorted(comparator).collect(Collectors.toList());
    }

}
