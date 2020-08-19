package com.db1group.buster.quarkus.command;

import com.db1group.buster.command.Command;
import com.db1group.buster.command.CommandHandler;
import com.db1group.buster.command.CommandHandlerFactory;
import com.db1group.buster.quarkus.NotFoundException;
import io.quarkus.arc.ClientProxy;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

@Dependent
public class CommandHandlerFactoryQuarkusImpl implements CommandHandlerFactory {

    @Any
    @Inject
    private Instance<CommandHandler<?>> commandHandlers;

    @Override
    public CommandHandler<Command> get(Command command) {
        return (CommandHandler<Command>) getHandler(command);
    }

    private CommandHandler<?> getHandler(Command command) {
        return commandHandlers
                .stream()
                .filter(handle -> filterByCommandTypeName((ClientProxy) handle, command.getClass().getTypeName()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Handler to Command " + command.getClass().getName() + " not found"));
    }

    private boolean filterByCommandTypeName(ClientProxy handle, String typeName) {
        final ParameterizedType parameterizedType = getParameterizedTypeFromBean(handle);
        return parameterizedType.getActualTypeArguments()[0].getTypeName().equals(typeName);
    }

    private ParameterizedType getParameterizedTypeFromBean(ClientProxy handle) {
        final Set<Type> beanTypes = handle.arc_bean().getTypes();
        return (ParameterizedType) beanTypes.stream()
                .filter(type -> type instanceof ParameterizedType)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Not found ParameterizedType to Bean " + handle.getClass().getName()));
    }


}
