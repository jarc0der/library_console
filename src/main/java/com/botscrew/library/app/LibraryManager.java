package com.botscrew.library.app;

import com.botscrew.library.commands.CommandRegister;
import com.botscrew.library.utils.io.Messanger;
import com.botscrew.library.utils.parser.CommandParser;
import com.botscrew.library.utils.parser.ParsedCommand;

public class LibraryManager {

    private boolean working = true;
    private CommandParser commandParser = new CommandParser();
    private CommandRegister commandRegister = new CommandRegister();

    public void run() {

        Messanger.write("Welcome to Secret Library!");

        while (working) {
            System.out.print(">>");

            String command = Messanger.read();

            if (command.equals("f")) {
                working = false;
                break;
            }

            try {
                ParsedCommand parsedCommand = commandParser.parse(command);

                commandRegister.executeCommand(parsedCommand.getCommandName(), parsedCommand.getParams());
            } catch (RuntimeException e) {
//                Messanger.write(e.getMessage());
                e.printStackTrace();
                continue;
            }
        }

    }

}
