package com.db1group.buster.common;

import com.db1group.buster.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandHandlerNotFoundExceptionTest {

    @Test
    void constructor() {
        CommandHandlerNotFoundException exception = new CommandHandlerNotFoundException(new FakeCommand());
        assertEquals("Handler to Command com.db1group.buster.common.CommandHandlerNotFoundExceptionTest$FakeCommand not found", exception.getMessage());
    }

    class FakeCommand implements Command {

    }
}