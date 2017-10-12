package com.botscrew.library.commands.users;

import com.botscrew.library.commands.Command;
import com.botscrew.library.persistance.entities.Book;
import com.botscrew.library.persistance.services.BookService;
import com.botscrew.library.persistance.services.BookServiceImpl;
import com.botscrew.library.utils.io.Messanger;

import java.util.Comparator;
import java.util.List;

public class ListBookCommandImpl implements Command {

    private BookService bookService = new BookServiceImpl();

    @Override
    public void execute() {
        Messanger.write("---- List ----");
        List<Book> retrievedBooks = bookService.getAllBooksOrdered(Comparator.comparing(Book::getName));

        printBookList(retrievedBooks);
    }

    private void printBookList(List<Book> books){

        books.stream().forEach(b -> Messanger.write(b.toString()));

    }

    @Override
    public void setParams(List<String> params) {

    }
}
