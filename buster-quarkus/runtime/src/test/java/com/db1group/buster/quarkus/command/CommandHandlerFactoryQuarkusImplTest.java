package com.db1group.buster.quarkus.command;

import com.db1group.buster.command.Command;
import com.db1group.buster.command.CommandHandler;
import com.db1group.buster.quarkus.common.ClientProxySpecification;
import com.db1group.buster.common.NotFoundException;
import io.quarkus.test.QuarkusUnitTest;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandHandlerFactoryQuarkusImplTest {

    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
            .setArchiveProducer(() ->
                    ShrinkWrap.create(JavaArchive.class)
                            .addClasses(
                                    FakeCommandHandler.class,
                                    FakeCommand.class,
                                    NotFoundFakeCommand.class,
                                    CommandHandlerFactoryQuarkusImpl.class,
                                    ClientProxySpecification.class)
            );

    @Inject
    private CommandHandlerFactoryQuarkusImpl commandHandlerFactoryQuarkus;

    @Inject
    private FakeCommandHandler fakeCommandHandler;

    @Test
    void get_whenHandlerImplementedToCommand_shouldReturnHandler() {
        CommandHandler<Command> handler = commandHandlerFactoryQuarkus.get(new FakeCommand());
        assertEquals(fakeCommandHandler, handler);
    }

    @Test
    void get_whenHandlerNotImplementedToCommand_shouldThrowNotFoundException() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> commandHandlerFactoryQuarkus.get(new NotFoundFakeCommand()));
        assertEquals("Handler to Command com.db1group.buster.quarkus.command.NotFoundFakeCommand not found", exception.getMessage());
    }
}