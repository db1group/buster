package com.db1group.buster.command;

import com.db1group.buster.command.Command;

public interface CommandHandler<T extends Command> {

    void apply(T command);
}
