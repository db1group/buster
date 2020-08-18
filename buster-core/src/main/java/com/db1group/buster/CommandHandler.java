package com.db1group.buster;

public interface CommandHandler<T extends Command<R>, R> {

    R apply(T command);
}
