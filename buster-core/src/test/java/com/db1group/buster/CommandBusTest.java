package com.db1group.buster;

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
    void execute_whenNullCommandArgument_shouldThrowException() {
        CommandBus commandBus = new CommandBus(commandHandlerFactory);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> commandBus.execute(null));
        assertEquals("Command should be not null", exception.getMessage());
    }

    @Test
    void execute_whenCalled_shouldGetHandlerAndApply() {
        String expected = "Command Applied";

        when(commandHandlerFactory.get(command)).thenReturn(commandHandler);
        when(commandHandler.apply(command)).thenReturn(expected);

        CommandBus commandBus = new CommandBus(commandHandlerFactory);

        Object result = commandBus.execute(command);

        assertEquals(result.toString(), expected);
        verify(commandHandlerFactory).get(command);
        verify(commandHandler).apply(command);
    }
}