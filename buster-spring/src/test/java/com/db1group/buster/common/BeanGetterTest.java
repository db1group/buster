package com.db1group.buster.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(classes = {ApplicationContext.class, BeanGetter.class, FakeSpringComponent.class})
class BeanGetterTest {

    @Autowired
    private BeanGetter beanGetter;

    @Test
    void get_whenExists_shouldReturnAOptionalWithBean() {
        Optional<FakeSpringComponent> beanOptional = beanGetter.get(FakeSpringComponent.class, String.class.getTypeName());
        assertTrue(beanOptional.isPresent());
        assertTrue(beanOptional.get() instanceof FakeSpringComponent);
    }

    @Test
    void get_whenNotExists_shouldReturnAOptionalEmpty() {
        Optional<FakeSpringComponent> beanOptional = beanGetter.get(FakeSpringComponent.class, Integer.class.getTypeName());
        assertFalse(beanOptional.isPresent());
    }
}