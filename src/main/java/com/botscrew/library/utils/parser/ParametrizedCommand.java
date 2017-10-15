package com.botscrew.library.utils.parser;

import java.util.List;

public class ParametrizedCommand {

    private String commandName;
    private List<String> params;

    protected ParametrizedCommand(String commandName, List<String> params) {
        this.commandName = commandName;
        this.params = params;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public int getParamsCount(){
        return params.size();
    }
}
