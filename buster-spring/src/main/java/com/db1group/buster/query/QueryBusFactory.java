package com.db1group.buster.query;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueryBusFactory {

    @Bean
    public QueryBus queryBus(QueryHandlerFactorySpringImpl queryHandlerFactorySpring) {
        return new QueryBus(queryHandlerFactorySpring);
    }
}
