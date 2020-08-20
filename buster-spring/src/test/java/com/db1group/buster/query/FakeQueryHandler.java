package com.db1group.buster.query;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class FakeQueryHandler implements QueryHandler<FakeQuery, String> {

    @Override
    public String apply(FakeQuery command) {
        return "fake";
    }
}
