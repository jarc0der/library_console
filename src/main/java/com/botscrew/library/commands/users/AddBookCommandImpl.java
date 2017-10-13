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
import com.botscrew.library.utils.parser.ParsedCommand;

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
        Author author = null;

        boolean answer = isChooseAuthor();

        if (!answer) {
            String command = Messanger.askWriteCommandOrData("Tip. To add new author use command: author [first name] [last name]");

            try {
                ParsedCommand parsedCommand = commandParser.parse(command);

                CommandRegister.executeCommand(parsedCommand.getCommandName(), parsedCommand.getParams());
            } catch (RuntimeException e) {
                Messanger.write(e.getMessage());
            }

            //retrieve last added author
            author = authorService.getLastAddedAuthor();
        } else {

            //show all authors
            List<Author> authorList = authorService.getAllAuthors();

            Messanger.write("---- List of authors ----");

            printAuthorList(authorList);

            int choose = Messanger.askChooseNumber("Enter number:");

            //retrieve author from list by id
            Author choosedAuthor = authorList.get(choose - 1);

            //set to local var
            author = choosedAuthor;
        }


        String bookName = Messanger.askWriteCommandOrData("Enter book name:");

        Book newBook = new Book(bookName);
        author.addBook(newBook);

        //save author
        bookService.addBook(newBook);

        Messanger.write("Book " + "\"" + newBook.getName() + "\"" + " for author " + author + " was added.");
    }

    private void printAuthorList(List<Author> authorList){

        AtomicInteger counter = new AtomicInteger(0);

        authorList.stream().forEach(a -> System.out.println(counter.incrementAndGet() + ". " + a));
    }

    private boolean isChooseAuthor(){
        Messanger.write("Do you want to choose author? y/n");

        return Messanger.read().equals("y");
    }

    @Override
    public void setParams(List<String> params) {

    }


}
