package com.db1group.buster.query;

public interface QueryHandler<T extends Query<R>, R> {

    R apply(T query);
}
