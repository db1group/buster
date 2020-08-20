package com.db1group.buster.query;

import com.db1group.buster.common.BeanGetter;
import com.db1group.buster.common.QueryHandlerNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class QueryHandlerFactorySpringImpl implements QueryHandlerFactory {

    private final BeanGetter beanGetter;

    public QueryHandlerFactorySpringImpl(BeanGetter beanGetter) {
        this.beanGetter = beanGetter;
    }

    @Override
    public <R> QueryHandler get(Query<R> query) {
        return beanGetter
                .get(QueryHandler.class, query.getClass().getTypeName())
                .orElseThrow(() -> new QueryHandlerNotFoundException(query));
    }
}
