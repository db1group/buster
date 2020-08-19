package com.db1group.buster.query;

import static java.util.Objects.requireNonNull;

public class QueryBus {

    private final QueryHandlerFactory queryHandlerFactory;

    public QueryBus(QueryHandlerFactory queryHandlerFactory) {
        requireNonNull(queryHandlerFactory, "QueryHandlerFactory should be not null");
        this.queryHandlerFactory = queryHandlerFactory;
    }

    public <R> R execute(Query<R> query) {
        requireNonNull(query, "Query should be not null");
        QueryHandler<Query<R>, R> handler = this.queryHandlerFactory.get(query);
        return handler.apply(query);
    }
}
