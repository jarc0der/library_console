package com.botscrew.library.persistance;

import com.botscrew.library.persistance.entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockDB {
    private List<Book> bookList = new ArrayList<>();

    public void addBook(Book book){
        bookList.add(book);
    }

    public void removeBook(Book book){
        bookList.remove(book);
    }

    public void editBook(String name, Book book){

        List<Book> editedListBookd = bookList.stream().filter(b -> b.equals(book)).map(b -> {b.setName(name); return b;}).collect(Collectors.toList());
        this.bookList = editedListBookd;

    }

    public List<Book> getAllBooks(){
        return bookList;
    }
}
