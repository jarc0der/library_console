package com.botscrew.library.persistance.repository;

import com.botscrew.library.persistance.MockDB;
import com.botscrew.library.persistance.entities.Book;

import java.util.List;

public class BookRepository {

    private static MockDB mockDB = new MockDB();

    public static void add(Book book){
        mockDB.addBook(book);
    }

    public static void remove(String bookName){

        List<Book> books = getAll();

        Book searchedBook = books.stream().filter(b -> b.getName().equals(bookName)).findFirst().get();

        mockDB.removeBook(searchedBook);

    }

    public static void update(Book book){
        Book currentBook = getAll().stream().filter(b -> b.getId() == book.getId()).findFirst().get();

        remove(getById(book.getId()).getName());

        add(book);

    }

    public static List<Book> getAll(){
        return mockDB.getAllBooks();
    }

    public static Book getById(int id){
       return getAll().stream().filter(b -> b.getId() == id).findFirst().get();
    }

}
