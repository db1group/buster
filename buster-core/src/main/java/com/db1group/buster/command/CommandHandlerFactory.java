package com.db1group.buster.command;

public interface CommandHandlerFactory {
    CommandHandler<Command> get(Command command);
}
