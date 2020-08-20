package com.db1group.buster.common;

import com.db1group.buster.query.Query;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryHandlerNotFoundExceptionTest {

    @Test
    void constructor() {
        QueryHandlerNotFoundException exception = new QueryHandlerNotFoundException(new FakeQuery());
        assertEquals("Handler to Query com.db1group.buster.common.QueryHandlerNotFoundExceptionTest$FakeQuery not found", exception.getMessage());
    }

    class FakeQuery implements Query<String> {
    }

}