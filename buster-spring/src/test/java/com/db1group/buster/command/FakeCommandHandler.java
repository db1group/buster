package com.db1group.buster.command;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class FakeCommandHandler implements CommandHandler<FakeCommand> {

    @Override
    public void apply(FakeCommand command) {

    }
}
