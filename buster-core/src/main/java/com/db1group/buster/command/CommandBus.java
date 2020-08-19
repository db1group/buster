package com.db1group.buster.command;

import static java.util.Objects.requireNonNull;

public class CommandBus {

    private final CommandHandlerFactory commandHandlerFactory;

    public CommandBus(CommandHandlerFactory commandHandlerFactory) {
        requireNonNull(commandHandlerFactory, "CommandHandlerFactory should be not null");
        this.commandHandlerFactory = commandHandlerFactory;
    }

    public void execute(Command command) {
        requireNonNull(command, "Command should be not null");
        CommandHandler<Command> handler = this.commandHandlerFactory.get(command);
        handler.apply(command);
    }
}
