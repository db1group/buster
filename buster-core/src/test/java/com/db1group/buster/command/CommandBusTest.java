package com.db1group.buster.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CommandBusTest {

    private final Command command = mock(Command.class);

    private final CommandHandler commandHandler = mock(CommandHandler.class);

    private final CommandHandlerFactory commandHandlerFactory = mock(CommandHandlerFactory.class);

    @Test
    void constructor_whenNullCommandHandleFactoryArgument_shouldThrowException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new CommandBus(null));
        assertEquals("CommandHandlerFactory should be not null", exception.getMessage());
    }

    @Test
    void execute_whenCommandWithNullCommandArgument_shouldThrowException() {
        Command command = null;
        CommandBus commandBus = new CommandBus(commandHandlerFactory);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> commandBus.execute(command));
        assertEquals("Command should be not null", exception.getMessage());
    }

    @Test
    void execute_whenCommand_shouldGetHandlerAndApply() {
        when(commandHandlerFactory.get(command)).thenReturn(commandHandler);
        CommandBus commandBus = new CommandBus(commandHandlerFactory);
        commandBus.execute(command);
        verify(commandHandlerFactory).get(command);
        verify(commandHandler).apply(command);
    }

//    @Test
//    void execute_whenCommandQueryWithNullQueryArgument_shouldThrowException() {
//        CommandQuery query = null;
//        CommandBus commandBus = new CommandBus(commandHandlerFactory);
//        NullPointerException exception = assertThrows(NullPointerException.class, () -> commandBus.execute(query));
//        assertEquals("Query should be not null", exception.getMessage());
//    }
//
//    @Test
//    void execute_whenCommandQuery_shouldGetHandlerAndApplyAndRerurnValue() {
//        when(commandHandlerFactory.get(commandQuery)).thenReturn(commandQueryHandler);
//        CommandBus commandBus = new CommandBus(commandHandlerFactory);
//        commandBus.execute(commandQuery);
//        verify(commandHandlerFactory).get(commandQuery);
//        verify(commandQueryHandler).apply(commandQuery);
//    }
}