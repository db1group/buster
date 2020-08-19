package com.db1group.buster.quarkus.query;

import com.db1group.buster.query.QueryHandler;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class FakeQueryHandler implements QueryHandler<FakeQuery, String> {

    @Override
    public String apply(FakeQuery command) {
        return "fake";
    }
}
