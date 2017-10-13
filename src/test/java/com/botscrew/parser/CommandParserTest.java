package com.botscrew.parser;

import com.botscrew.library.utils.parser.CommandParser;
import com.botscrew.library.utils.parser.ParsedCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandParserTest {

    private CommandParser parser = new CommandParser();

    @Test
    public void simpleParseTest(){
        String command = "command1 param1 param2";

        ParsedCommand command1 = parser.parse(command);

        assertEquals(command1.getCommandName(), "command1");
        assertEquals(command1.getParams().size(), 2);
    }

    @Test
    public void complicatedParseTest(){
        String command = "command1 'sub-param1 sub-param2 sub-param3' 'param3' param4";

        ParsedCommand command1 = parser.parse(command);

        assertEquals("command1", command1.getCommandName());
        assertEquals(3, command1.getParams().size());
    }

    @Test
    public void onlyCommandTest(){
        String command = "command1";

        ParsedCommand command1 = parser.parse(command);

        assertEquals("command1", command1.getCommandName());
        assertEquals(0, command1.getParams().size());
    }

    @Test
    public void onlySimpleCommandTest(){
        String command = "command1 param1 param2";

        ParsedCommand command1 = parser.parse(command);

        assertEquals("command1", command1.getCommandName());
        assertEquals(2, command1.getParams().size());
    }


}
