package com.db1group.buster.command;

import com.db1group.buster.common.BeanGetter;
import com.db1group.buster.common.CommandHandlerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest(classes = {ApplicationContext.class, BeanGetter.class, CommandHandlerFactorySpringImpl.class, FakeCommandHandler.class})
class CommandHandlerFactorySpringImplTest {

    @Autowired
    private CommandHandlerFactorySpringImpl commandHandlerFactorySpring;

    @Autowired
    private FakeCommandHandler fakeCommandHandler;

    @Test
    void get_whenHandlerImplementedToCommand_shouldReturnHandler() {
        CommandHandler<Command> commandHandler = commandHandlerFactorySpring.get(new FakeCommand());
        Assertions.assertEquals(fakeCommandHandler, commandHandler);
    }

    @Test
    void get_whenHandlerNotImplementedToCommand_shouldThrowNotFoundException() {
        CommandHandlerNotFoundException exception = assertThrows(CommandHandlerNotFoundException.class, () -> commandHandlerFactorySpring.get(new NotFoundFakeCommand()));
        assertEquals("Handler to Command com.db1group.buster.command.NotFoundFakeCommand not found", exception.getMessage());
    }
}