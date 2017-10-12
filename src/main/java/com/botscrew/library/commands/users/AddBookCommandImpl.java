package com.botscrew.library.commands.users;

import com.botscrew.library.commands.Command;
import com.botscrew.library.persistance.entities.Book;
import com.botscrew.library.persistance.services.BookService;
import com.botscrew.library.persistance.services.BookServiceImpl;

import java.util.List;

public class AddBookCommandImpl implements Command {

    private BookService bookService = new BookServiceImpl();
    private List<String> params;

    @Override
    public void execute() {
        Book book = new Book();

        book.setAuthor(params.get(0));
        book.setName(params.get(1));

        bookService.addBook(book);

        System.out.println("Added new book " + book);
    }

    @Override
    public void setParams(List<String> params) {
        if(params.size() != 2)
            throw new IllegalArgumentException("Wrong parameter count!");

        this.params = params;
    }


}
