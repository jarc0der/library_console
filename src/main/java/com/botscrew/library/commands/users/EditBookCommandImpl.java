package com.botscrew.library.commands.users;

import com.botscrew.library.commands.Command;
import com.botscrew.library.exceptions.IllegalBookListNumberException;
import com.botscrew.library.exceptions.NoSuchBookFoundException;
import com.botscrew.library.persistance.entities.Book;
import com.botscrew.library.persistance.services.BookService;
import com.botscrew.library.persistance.services.impl.BookServiceImpl;
import com.botscrew.library.utils.io.Messenger;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EditBookCommandImpl implements Command {

    private BookService bookService = new BookServiceImpl();
    private List<String> params;

    @Override
    public void execute() {

        String bookName = params.get(0);

        List<Book> bookList = bookService.getBooksByName(bookName);

        if (bookList.size() == 0)
            throw new NoSuchBookFoundException("Can't find book with name " + bookName);

        Book bookForUpdate = null;
        String oldBookName = null;

        if (bookList.size() == 1) {
            bookForUpdate = bookList.get(0);
            oldBookName = bookForUpdate.getName();
        } else {
            showBookSubList(bookList);
            int userListChoice = Messenger.askChooseNumber("Enter number from list:");

            if (userListChoice > bookList.size() || userListChoice < 1)
                throw new IllegalBookListNumberException("Illegal list number " + userListChoice);

            bookForUpdate = bookList.get(userListChoice - 1);
            oldBookName = bookForUpdate.getName();
        }

        String updatedName = Messenger.askWriteCommandOrData("Enter new name for book:");

        bookForUpdate.setName(updatedName);

        bookService.updateBook(bookForUpdate);

        Messenger.write("Name of book " + "\"" + oldBookName + "\"" + " was changed to " + "\"" + bookForUpdate.getName() + "\"");

    }

    private void showBookSubList(List<Book> bookList) {

        AtomicInteger counter = new AtomicInteger(0);

        bookList.stream().forEach(b -> Messenger.write(counter.incrementAndGet() + ". " + b.getName()));
    }


    @Override
    public void setParams(List<String> params) {
        if(params.size() != 1)
            throw new IllegalArgumentException("Wrong parameters count.");

        this.params = params;
    }
}
