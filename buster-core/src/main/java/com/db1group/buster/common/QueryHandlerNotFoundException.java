package com.db1group.buster.common;

import com.db1group.buster.query.Query;

public class QueryHandlerNotFoundException extends RuntimeException {

    public QueryHandlerNotFoundException(Query query) {
        super("Handler to Query " + query.getClass().getName() + " not found");
    }
}
