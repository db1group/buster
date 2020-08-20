package com.db1group.buster.command;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommandBusFactory {

    @Bean
    public CommandBus commandBus(CommandHandlerFactorySpringImpl commandHandlerFactorySpring) {
        return new CommandBus(commandHandlerFactorySpring);
    }

}
