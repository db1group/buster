package com.db1group.buster.quarkus.command;

import com.db1group.buster.command.CommandHandler;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class FakeCommandHandler implements CommandHandler<FakeCommand> {

    @Override
    public void apply(FakeCommand command) {

    }
}
