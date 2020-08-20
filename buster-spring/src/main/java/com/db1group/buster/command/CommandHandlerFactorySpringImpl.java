package com.db1group.buster.command;

import com.db1group.buster.common.BeanGetter;
import com.db1group.buster.common.CommandHandlerNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CommandHandlerFactorySpringImpl implements CommandHandlerFactory {

    private final BeanGetter beanGetter;

    public CommandHandlerFactorySpringImpl(BeanGetter beanGetter) {
        this.beanGetter = beanGetter;
    }

    @Override
    public CommandHandler get(Command command) {
        return beanGetter
                .get(CommandHandler.class, command.getClass().getTypeName())
                .orElseThrow(() -> new CommandHandlerNotFoundException(command));
    }
}
