package com.botscrew.library.app;

import com.botscrew.library.commands.CommandRegister;
import com.botscrew.library.utils.io.Messenger;
import com.botscrew.library.utils.parser.CommandParser;
import com.botscrew.library.utils.parser.ParametrizedCommand;

public class LibraryManager {

    private boolean working = true;
    private CommandParser commandParser = new CommandParser();
    private CommandRegister commandRegister = new CommandRegister();

    public void run() {

        Messenger.write("Welcome to Secret Library!");

        while (working) {
            System.out.print(">>");

            String command = Messenger.read();

            if (command.equals("q")) {
                working = false;
                break;
            }

            try {
                ParametrizedCommand parametrizedCommand = commandParser.parse(command);

                CommandRegister.executeCommand(parametrizedCommand.getCommandName(), parametrizedCommand.getParams());
            } catch (RuntimeException e) {
                Messenger.write(e.getMessage());
//                e.printStackTrace();
            }
        }

    }

}
