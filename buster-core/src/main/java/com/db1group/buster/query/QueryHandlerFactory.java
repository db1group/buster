package com.db1group.buster.query;

public interface QueryHandlerFactory {
    <R> QueryHandler<Query<R>, R> get(Query<R> query);
}
