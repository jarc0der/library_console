package com.botscrew.library.commands;

import com.botscrew.library.commands.users.*;
import com.botscrew.library.exceptions.UnknownCommandException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandRegister {

    private static Map<String, Command> commandList;

    public CommandRegister() {
        commandList = new HashMap<>();
        addCommand("add", new AddBookCommandImpl());
        addCommand("remove", new RemoveBookCommandImpl());
        addCommand("edit", new EditBookCommandImpl());
        addCommand("list", new ListBookCommandImpl());
        addCommand("author", new AddAuthorCommandImpl());
    }

    private static void addCommand(String name, Command command) {

        commandList.put(name, command);

    }

    //TODO: Refactor
    public static void executeCommand(String name, List<String> params) {

        if(!checkCommandExists(name))
            throw new UnknownCommandException("We can't find command " + name);

        Command command = commandList.get(name);


        if (params == null) {
            params = new ArrayList<>();
        } else {
            command.setParams(params);
        }

        command.execute();
    }

    private static boolean checkCommandExists(String command){
        return commandList.containsKey(command);
    }

}
