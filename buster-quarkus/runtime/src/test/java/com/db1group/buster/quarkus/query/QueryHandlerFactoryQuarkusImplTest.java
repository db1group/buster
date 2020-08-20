package com.db1group.buster.quarkus.query;

import com.db1group.buster.quarkus.common.ClientProxySpecification;
import com.db1group.buster.common.NotFoundException;
import com.db1group.buster.query.Query;
import com.db1group.buster.query.QueryHandler;
import io.quarkus.test.QuarkusUnitTest;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueryHandlerFactoryQuarkusImplTest {

    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
            .setArchiveProducer(() ->
                    ShrinkWrap.create(JavaArchive.class)
                            .addClasses(
                                    FakeQueryHandler.class,
                                    FakeQuery.class,
                                    NotFoundFakeQuery.class,
                                    QueryHandlerFactoryQuarkusImpl.class,
                                    ClientProxySpecification.class)
            );

    @Inject
    private QueryHandlerFactoryQuarkusImpl queryHandlerFactoryQuarkus;

    @Inject
    private FakeQueryHandler fakeCommandHandler;

    @Test
    void get_whenHandlerImplementedToCommand_shouldReturnHandler() {
        QueryHandler<Query<String>, String> handler = queryHandlerFactoryQuarkus.get(new FakeQuery());
        assertEquals(fakeCommandHandler, handler);
    }

    @Test
    void get_whenHandlerNotImplementedToCommand_shouldReturnHandler() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> queryHandlerFactoryQuarkus.get(new NotFoundFakeQuery()));
        assertEquals("Handler to Query com.db1group.buster.quarkus.query.NotFoundFakeQuery not found", exception.getMessage());
    }

}