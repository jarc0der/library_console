package com.botscrew.library.commands.users;

import com.botscrew.library.commands.Command;
import com.botscrew.library.persistance.services.BookService;
import com.botscrew.library.persistance.services.BookServiceImpl;
import com.botscrew.library.utils.io.Messanger;

import java.util.List;

public class RemoveBookCommandImpl implements Command{

    private List<String> params;
    private BookService bookService = new BookServiceImpl();


    @Override
    public void execute() {
        Messanger.write("Enter book name");

        String bookName = Messanger.read();

        bookService.removeBookByName(bookName);

    }

    @Override
    public void setParams(List<String> params) {
        this.params = params;
    }
}
