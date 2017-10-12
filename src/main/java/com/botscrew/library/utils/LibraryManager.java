package com.botscrew.library.utils;

import com.botscrew.library.commands.CommandRegister;
import com.botscrew.library.utils.io.Messanger;
import com.botscrew.library.utils.parser.CommandParser;
import com.botscrew.library.utils.parser.ParsedCommand;

public class LibraryManager {

    private boolean working = true;
    private CommandParser commandParser = new CommandParser();
    private CommandRegister commandRegister = new CommandRegister();

    public void run() {

        Messanger.write("Welcome to Library!");

        while (working) {
            System.out.print(">>");

            String command = Messanger.read();

            if (command.equals("f")) {
                working = false;
                return;
            }

            try{
                ParsedCommand parsedCommand = commandParser.parse(command);

                commandRegister.executeCommand(parsedCommand.getCommandName(), parsedCommand.getParams());
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
                continue;
            }

            System.out.println("END");

        }

    }

}
