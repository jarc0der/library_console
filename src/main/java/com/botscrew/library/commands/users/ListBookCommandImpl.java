package com.botscrew.library.commands.users;

import com.botscrew.library.commands.Command;
import com.botscrew.library.persistance.entities.Book;
import com.botscrew.library.persistance.services.BookService;
import com.botscrew.library.persistance.services.impl.BookServiceImpl;
import com.botscrew.library.utils.io.Messanger;

import java.util.Comparator;
import java.util.List;

public class ListBookCommandImpl implements Command {

    private BookService bookService = new BookServiceImpl();

    @Override
    public void execute() {
        List<Book> retrievedBooks = bookService.getAllBooksOrdered(Comparator.comparing(Book::getName));

        if(retrievedBooks.size() == 0){
            Messanger.write("Unfortunately, we can't find any book");
            Messanger.write("Tip. To add new book type: add");
            return;
        }

        Messanger.write("---- Book List ----");
        printBookList(retrievedBooks);
    }

    private void printBookList(List<Book> books){

        books.stream().forEach(b -> Messanger.write("Book: " + "\"" +b.getName() + "\"\n\t" + " Author: " + b.getAuthor()));

    }

    @Override
    public void setParams(List<String> params) {
        //ignored for this command
    }
}
