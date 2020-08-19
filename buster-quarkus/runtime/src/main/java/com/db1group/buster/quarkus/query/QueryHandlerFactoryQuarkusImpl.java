package com.db1group.buster.quarkus.query;

import com.db1group.buster.quarkus.common.NotFoundException;
import com.db1group.buster.query.Query;
import com.db1group.buster.query.QueryHandler;
import com.db1group.buster.query.QueryHandlerFactory;
import io.quarkus.arc.ClientProxy;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import static com.db1group.buster.quarkus.common.ClientProxySpecification.hasParameterizedTypeName;

@Dependent
public class QueryHandlerFactoryQuarkusImpl implements QueryHandlerFactory {

    @Any
    @Inject
    private Instance<QueryHandler<?, ?>> handlers;

    @Override
    public <R> QueryHandler<Query<R>, R> get(Query<R> query) {
        return (QueryHandler<Query<R>, R>) getHandler(query);
    }

    private <R> QueryHandler<?, ?> getHandler(Query<R> query) {
        return handlers
                .stream()
                .filter(handle -> hasParameterizedTypeName((ClientProxy) handle, query.getClass().getTypeName()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Handler to Query " + query.getClass().getName() + " not found"));
    }
}
