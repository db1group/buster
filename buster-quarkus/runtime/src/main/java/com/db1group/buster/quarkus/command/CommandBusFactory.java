package com.db1group.buster.quarkus.command;

import com.db1group.buster.command.CommandBus;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class CommandBusFactory {

    private final CommandHandlerFactoryQuarkusImpl commandHandlerFactoryQuarkus;

    public CommandBusFactory(CommandHandlerFactoryQuarkusImpl commandHandlerFactoryQuarkus) {
        this.commandHandlerFactoryQuarkus = commandHandlerFactoryQuarkus;
    }

    @Produces
    public CommandBus commandBus() {
        return new CommandBus(commandHandlerFactoryQuarkus);
    }
}
