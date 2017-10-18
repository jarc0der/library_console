package com.botscrew.library.commands.users;

import com.botscrew.library.commands.Command;
import com.botscrew.library.persistance.entities.Author;
import com.botscrew.library.persistance.services.AuthorService;
import com.botscrew.library.persistance.services.impl.AuthorServiceImpl;
import com.botscrew.library.utils.io.Messenger;

import java.util.List;

public class AddAuthorCommandImpl implements Command {

    private AuthorService authorService = new AuthorServiceImpl();
    private List<String> params;

    @Override
    public void execute() {

        String authorFirstName = params.get(0);
        String authorLastName = params.get(1);

        Author author = new Author(authorFirstName, authorLastName);

        authorService.addAuthor(author);

        Messenger.write("Author " + "\"" + author + "\"" + " was added");

    }

    @Override
    public void setParams(List<String> params) {
        this.params = params;
    }
}
