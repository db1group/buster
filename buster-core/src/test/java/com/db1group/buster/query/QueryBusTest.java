package com.db1group.buster.query;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class QueryBusTest {

    private final Query query = mock(Query.class);

    private final QueryHandler queryHandler = mock(QueryHandler.class);

    private final QueryHandlerFactory queryHandlerFactory = mock(QueryHandlerFactory.class);

    @Test
    void constructor_whenNullQueryHandleFactoryArgument_shouldThrowException() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new QueryBus(null));
        assertEquals("QueryHandlerFactory should be not null", exception.getMessage());
    }

    @Test
    void execute_whenQueryWithNullCommandArgument_shouldThrowException() {
        Query query = null;
        QueryBus queryBus = new QueryBus(queryHandlerFactory);
        NullPointerException exception = assertThrows(NullPointerException.class, () -> queryBus.execute(query));
        assertEquals("Query should be not null", exception.getMessage());
    }

    @Test
    void execute_whenQuery_shouldGetHandlerAndApplyAndRerurnValue() {
        String expected = "result";
        when(queryHandlerFactory.get(query)).thenReturn(queryHandler);
        when(queryHandler.apply(query)).thenReturn(expected);
        QueryBus queryBus = new QueryBus(queryHandlerFactory);
        Object result = queryBus.execute(query);

        assertEquals(expected, result.toString());
        verify(queryHandlerFactory).get(query);
        verify(queryHandler).apply(query);
    }
}