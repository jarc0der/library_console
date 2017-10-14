package com.botscrew.library.commands.users;

import com.botscrew.library.commands.Command;
import com.botscrew.library.persistance.services.BookService;
import com.botscrew.library.persistance.services.impl.BookServiceImpl;
import com.botscrew.library.utils.io.Messanger;

import java.util.List;

public class RemoveBookCommandImpl implements Command{

    private List<String> params;
    private BookService bookService = new BookServiceImpl();


    @Override
    public void execute() {
        String bookName = params.get(0);

        boolean removed = bookService.removeBookByName(bookName);

        if ((removed)) {
            Messanger.write("Book " + bookName + " was removed");
        } else {
            Messanger.write("Book with name " + "\"" + bookName + "\"" + " wasn't found.");
        }
    }

    @Override
    public void setParams(List<String> params) {
        if(params.size() != 1)
            throw  new IllegalArgumentException("Wrong params count");

        this.params = params;
    }
}
