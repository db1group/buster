package com.db1group.buster;

import static java.util.Objects.requireNonNull;

public class CommandBus {

    private final CommandHandlerFactory handlerFactory;

    public CommandBus(CommandHandlerFactory handlerFactory) {
        requireNonNull(handlerFactory, "CommandHandlerFactory should be not null");
        this.handlerFactory = handlerFactory;
    }

    public <R> R execute(Command<R> command) {
        requireNonNull(command, "Command should be not null");
        CommandHandler<Command<R>, R> handler = this.handlerFactory.get(command);
        return handler.apply(command);
    }
}
