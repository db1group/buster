package com.db1group.buster.quarkus.command;

import com.db1group.buster.command.Command;
import com.db1group.buster.command.CommandHandler;
import com.db1group.buster.command.CommandHandlerFactory;
import com.db1group.buster.quarkus.common.NotFoundException;
import io.quarkus.arc.ClientProxy;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import static com.db1group.buster.quarkus.common.ClientProxySpecification.hasParameterizedTypeName;

@Dependent
public class CommandHandlerFactoryQuarkusImpl implements CommandHandlerFactory {

    @Any
    @Inject
    private Instance<CommandHandler<?>> handlers;

    @Override
    public CommandHandler<Command> get(Command command) {
        return (CommandHandler<Command>) getHandler(command);
    }

    private CommandHandler<?> getHandler(Command command) {
        return handlers
                .stream()
                .filter(handle -> hasParameterizedTypeName((ClientProxy) handle, command.getClass().getTypeName()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Handler to Command " + command.getClass().getName() + " not found"));
    }
}
