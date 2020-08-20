package com.db1group.buster.common;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Profile("test")
@Component
public class FakeSpringComponent implements Consumer<String> {

    @Override
    public void accept(String s) {

    }
}
