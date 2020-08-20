package com.db1group.buster.query;

import com.db1group.buster.common.BeanGetter;
import com.db1group.buster.common.QueryHandlerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest(classes = {ApplicationContext.class, BeanGetter.class, QueryHandlerFactorySpringImpl.class, FakeQueryHandler.class})
class QueryHandlerFactorySpringImplTest {

    @Autowired
    private QueryHandlerFactorySpringImpl queryHandlerFactorySpring;

    @Autowired
    private FakeQueryHandler fakeQueryHandler;

    @Test
    void get_whenHandlerImplementedToQuery_shouldReturnHandler() {
        QueryHandler<Query<String>, String> commandHandler = queryHandlerFactorySpring.get(new FakeQuery());
        Assertions.assertEquals(fakeQueryHandler, commandHandler);
    }

    @Test
    void get_whenHandlerNotImplementedToQuery_shouldThrowNotFoundException() {
        QueryHandlerNotFoundException exception = assertThrows(QueryHandlerNotFoundException.class, () -> queryHandlerFactorySpring.get(new NotFoundFakeQuery()));
        assertEquals("Handler to Query com.db1group.buster.query.NotFoundFakeQuery not found", exception.getMessage());
    }

}