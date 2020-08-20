package com.db1group.buster.common;

import com.db1group.buster.command.Command;

public class CommandHandlerNotFoundException extends RuntimeException {

    public CommandHandlerNotFoundException(Command command) {
        super("Handler to Command " + command.getClass().getName() + " not found");
    }
}
