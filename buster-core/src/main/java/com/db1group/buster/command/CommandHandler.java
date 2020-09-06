package com.db1group.buster.command;

public interface CommandHandler<T extends Command> {

    void apply(T command);
}
