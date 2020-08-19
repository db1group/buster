package com.db1group.buster.quarkus;

import com.db1group.buster.quarkus.common.NotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotFoundExceptionTest {

    @Test
    void constructor() {
        String expected = "Not Found Message";
        NotFoundException exception = new NotFoundException(expected);
        assertEquals(expected, exception.getMessage());
    }
}