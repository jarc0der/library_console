package com.botscrew.library.commands;

import com.botscrew.library.commands.users.AddBookCommandImpl;
import com.botscrew.library.commands.users.EditBookCommandImpl;
import com.botscrew.library.commands.users.ListBookCommandImpl;
import com.botscrew.library.commands.users.RemoveBookCommandImpl;
import com.botscrew.library.exceptions.UnknownCommandException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandRegister {

    private Map<String, Command> commandList;

    public CommandRegister() {
        this.commandList = new HashMap<>();
        addCommand("add", new AddBookCommandImpl());
        addCommand("remove", new RemoveBookCommandImpl());
        addCommand("edit", new EditBookCommandImpl());
        addCommand("list", new ListBookCommandImpl());
    }

    private void addCommand(String name, Command command) {

        this.commandList.put(name, command);

    }

    //TODO: Refactor
    public void executeCommand(String name, List<String> params) {

        if(!checkCommandExists(name))
            throw new UnknownCommandException("We can't find command " + name);

        Command command = this.commandList.get(name);


        if (params == null) {
            params = new ArrayList<>();
        } else {
            command.setParams(params);
        }

        command.execute();
    }

    private boolean checkCommandExists(String command){
        return commandList.containsKey(command);
    }

}
