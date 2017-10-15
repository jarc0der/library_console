package com.botscrew.library.utils.parser;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {

    public ParametrizedCommand parse(String data) {

        List<String> params = new ArrayList<>();

        String delimiter = " ";

        int position = data.indexOf(delimiter);

        if(position == -1){
            return new ParametrizedCommand(data, new ArrayList<>());
        }

        String command = data.substring(0, position);

        try{
            while (position < data.length()) {
                if (data.charAt(position + 1) == '\'') {
                    int index = data.indexOf('\'', position + 2);

                    params.add(data.substring(position + 2, index));

                    position = index + 1;
                } else {
                    //get next delimiter position
                    int index = data.indexOf(delimiter, position + 2);

                    //define end of the String
                    if (index == -1) {
                        index = data.length();
                    }

                    params.add(data.substring(position + 1, index));

                    position = index;
                }
            }
            return new ParametrizedCommand(command, params);
        }catch (RuntimeException e){
            throw new IllegalArgumentException("Wrong params for this command");
        }
    }

}
