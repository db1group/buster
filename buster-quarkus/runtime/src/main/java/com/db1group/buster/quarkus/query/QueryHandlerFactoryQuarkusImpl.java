package com.db1group.buster.quarkus.query;

import com.db1group.buster.command.Command;
import com.db1group.buster.quarkus.NotFoundException;
import com.db1group.buster.query.Query;
import com.db1group.buster.query.QueryHandler;
import com.db1group.buster.query.QueryHandlerFactory;
import io.quarkus.arc.ClientProxy;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

@Dependent
public class QueryHandlerFactoryQuarkusImpl implements QueryHandlerFactory {

    @Any
    @Inject
    private Instance<QueryHandler<?, ?>> handlers;

    @Override
    public <R> QueryHandler<Query<R>, R> get(Query<R> query) {
        return (QueryHandler<Query<R>, R>) getHandler(query);
    }

    private QueryHandler<?, ?> getHandler(Command command) {
        return handlers
                .stream()
                .filter(handle -> filterByCommandTypeName((ClientProxy) handle, command.getClass().getTypeName()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Handler to Command " + command.getClass().getName() + " not found"));
    }

    private <R> QueryHandler<?, ?> getHandler(Query<R> query) {
        return handlers
                .stream()
                .filter(handle -> filterByCommandTypeName((ClientProxy) handle, query.getClass().getTypeName()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Handler to Command Query " + query.getClass().getName() + " not found"));
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
