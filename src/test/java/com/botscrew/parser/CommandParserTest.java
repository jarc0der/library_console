package com.botscrew.parser;

import com.botscrew.library.utils.parser.CommandParser;
import com.botscrew.library.utils.parser.ParsedCommand;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
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
    @UseDataProvider("simpleCommands")
    public void onlySimpleCommandTest(String input, String expectedCommand, String param, int expectedParamsCount){
        ParsedCommand command1 = parser.parse(input);

        assertEquals(expectedCommand, command1.getCommandName());

        assertEquals(param, command1.getParams().get(0));

        assertEquals(expectedParamsCount, command1.getParams().size());
    }

    @DataProvider
    public static Object[][] simpleCommands(){
        return new Object[][]{
                {
                    "com param1",
                    "param1",
                    1
                },
                {
                    "com 'param1'",
                    "param1",
                    1
                },
        };
    }

}
