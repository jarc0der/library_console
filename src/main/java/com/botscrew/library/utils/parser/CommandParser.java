package com.botscrew.library.utils.parser;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {

    public ParsedCommand parse(String data) {

        String[] parsedData = data.split(" ");

        String commandName = parsedData[0];

        List<String> params = null;

        if (parsedData.length > 1) {
            params = retrieveParams(parsedData);
        }

        return new ParsedCommand(commandName, params);
    }

    private List<String> retrieveParams(String[] data) {
        List<String> params = new ArrayList<>();

        for (int i = 1; i < data.length; i++) {
            params.add(data[i]);
        }

        return params;

    }


}
