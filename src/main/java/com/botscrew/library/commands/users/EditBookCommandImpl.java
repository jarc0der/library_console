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


        //TODO: Refactoring

        List<Book> bookList = bookService.getBooksByName(bookName);
        int bookNumber = 0;

        if (bookList.size() == 0)
            throw new NoSuchBookFoundException("Can't find book with name " + bookName);

        if (bookList.size() == 1) {

            Messanger.write("Enter new name");

            String updatedName = Messanger.read();

            Book book = bookList.get(0);

            book.setName(updatedName);

            bookService.updateBook(book);


        } else {
            showBookSubList(bookList);
            Messanger.write("Enter list number:");

            String listNumber = Messanger.read();

            bookNumber = Integer.parseInt(listNumber);

            if(bookNumber > bookList.size() - 1 || bookNumber < 1)
                throw new IllegalBookListNumberException("Illegal list number " + bookNumber);

            Messanger.write("Enter new name");

            String updatedName = Messanger.read();

            Book book = null;

            book = bookList.get(bookNumber - 1);

            book.setName(updatedName);

            bookService.updateBook(book);
        }


    }

    private void showBookSubList(List<Book> bookList) {

        AtomicInteger counter = new AtomicInteger(0);

        bookList.stream().forEach(b -> Messanger.write(counter.incrementAndGet() + ". " + b.getName()));


    }

    @Override
    public void setParams(List<String> params) {

    }
}
