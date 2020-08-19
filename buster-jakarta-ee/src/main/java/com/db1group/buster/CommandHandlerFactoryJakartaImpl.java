package com.db1group.buster;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class CommandBusJakartaImpl implements CommandHandlerFactory {

    @Any
    @Inject
    private Instance<CommandHandler<?, ?>> handlers;


    @Override
    public <R> CommandHandler<Command<R>, R> get(Command<R> command) {
        return null;
    }
}
