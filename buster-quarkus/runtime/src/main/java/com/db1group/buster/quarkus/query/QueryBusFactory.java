package com.db1group.buster.quarkus.query;

import com.db1group.buster.query.QueryBus;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class QueryBusFactory {

    private final QueryHandlerFactoryQuarkusImpl queryHandlerFactoryQuarkus;

    public QueryBusFactory(QueryHandlerFactoryQuarkusImpl queryHandlerFactoryQuarkus) {
        this.queryHandlerFactoryQuarkus = queryHandlerFactoryQuarkus;
    }

    @Produces
    public QueryBus commandBus() {
        return new QueryBus(queryHandlerFactoryQuarkus);
    }
}
