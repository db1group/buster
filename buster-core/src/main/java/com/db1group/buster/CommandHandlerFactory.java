package com.db1group.buster;

public interface CommandHandlerFactory {
    <R> CommandHandler<Command<R>, R> get(Command<R> command);
}
