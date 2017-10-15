package com.botscrew.library.commands.users;

import com.botscrew.library.commands.Command;
import com.botscrew.library.commands.CommandRegister;
import com.botscrew.library.persistance.entities.Author;
import com.botscrew.library.persistance.entities.Book;
import com.botscrew.library.persistance.services.AuthorService;
import com.botscrew.library.persistance.services.impl.AuthorServiceImpl;
import com.botscrew.library.persistance.services.BookService;
import com.botscrew.library.persistance.services.impl.BookServiceImpl;
import com.botscrew.library.utils.io.Messanger;
import com.botscrew.library.utils.parser.CommandParser;
import com.botscrew.library.utils.parser.ParametrizedCommand;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AddBookCommandImpl implements Command {

    private BookService bookService = new BookServiceImpl();
    private AuthorService authorService = new AuthorServiceImpl();
    private CommandParser commandParser = new CommandParser();

    //ignored in this command
    private List<String> params;

    @Override
    public void execute() {
        Author author;

        boolean answer = isChooseAuthor();
        int chosenNumber;

        if (!answer) {
            String command = Messanger.askWriteCommandOrData("Tip. To add new author use command: author [first name] [last name]");

            try {
                ParametrizedCommand parametrizedCommand = commandParser.parse(command);

                CommandRegister.executeCommand(parametrizedCommand.getCommandName(), parametrizedCommand.getParams());
            } catch (RuntimeException e) {
                Messanger.write(e.getMessage());
            }

            //retrieve last added author
            author = authorService.getLastAddedAuthor();
        } else {

            //show all authors
            List<Author> authorList = authorService.getAllAuthors();

            if (authorList.size() == 0) {
                Messanger.write("Unfortunately there isn't any author.");
                Messanger.write("Tip. You can add new author: author [first_name] [last_name]");
                return;
            } else {
                Messanger.write("---- List of authors ----");
                printAuthorList(authorList);
                chosenNumber = Messanger.askChooseNumber("Enter number:");
            }

            //set to local var
            author = authorList.get(chosenNumber - 1);
        }


        String bookName = Messanger.askWriteCommandOrData("Enter book name:");

        Book newBook = new Book(bookName);
        author.addBook(newBook);

        //save author
        bookService.addBook(newBook);

        Messanger.write("Book " + "\"" + newBook.getName() + "\"" + " for author " + author + " was added.");
    }

    private void printAuthorList(List<Author> authorList) {

        AtomicInteger counter = new AtomicInteger(0);

        authorList.stream().forEach(a -> System.out.println(counter.incrementAndGet() + ". " + a));
    }

    private boolean isChooseAuthor() {
        Messanger.write("Do you want to choose author? y/n");

        return Messanger.read().equals("y");
    }

    @Override
    public void setParams(List<String> params) {
        //ignored for this command
    }


}
