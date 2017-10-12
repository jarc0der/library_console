package com.botscrew.library.commands.users;

import com.botscrew.library.commands.Command;
import com.botscrew.library.exceptions.IllegalBookListNumberException;
import com.botscrew.library.exceptions.NoSuchBookFoundException;
import com.botscrew.library.persistance.entities.Book;
import com.botscrew.library.persistance.services.BookService;
import com.botscrew.library.persistance.services.BookServiceImpl;
import com.botscrew.library.utils.io.Messanger;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EditBookCommandImpl implements Command {

    private BookService bookService = new BookServiceImpl();

    @Override
    public void execute() {
        Messanger.write("Enter book name");

        String bookName = Messanger.read();

        List<Book> bookList = bookService.getBooksByName(bookName);

        if (bookList.size() == 0)
            throw new NoSuchBookFoundException("Can't find book with name " + bookName);

        Book bookForUpdate = null;

        if (bookList.size() == 1) {
            bookForUpdate = bookList.get(0);
        } else {
            showBookSubList(bookList);
            int userListChoice = askBookListNumber();

            if (userListChoice > bookList.size() - 1 || userListChoice < 1)
                throw new IllegalBookListNumberException("Illegal list number " + userListChoice);

            bookForUpdate = bookList.get(userListChoice - 1);
        }

        String updatedName = askNewBookName();

        bookForUpdate.setName(updatedName);

        bookService.updateBook(bookForUpdate);

    }

    private void showBookSubList(List<Book> bookList) {

        AtomicInteger counter = new AtomicInteger(0);

        bookList.stream().forEach(b -> Messanger.write(counter.incrementAndGet() + ". " + b.getName()));
    }

    private String askNewBookName() {
        Messanger.write("Enter new book name");

        return Messanger.read();
    }

    private int askBookListNumber() {
        Messanger.write("Enter list number:");
        String listNumber = Messanger.read();

        return Integer.parseInt(listNumber);
    }

    @Override
    public void setParams(List<String> params) {

    }
}
