package com.db1group.buster.common;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;

@Component
public class BeanGetter {

    private final ApplicationContext applicationContext;

    public BeanGetter(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <T> Optional<T> get(Class<T> aClass, String parameterizedTypeName) {
        return applicationContext
                .getBeansOfType(aClass)
                .values()
                .stream()
                .filter(handler -> filterByParameterizedTypeName(parameterizedTypeName, handler))
                .findFirst();
    }

    private <T> boolean filterByParameterizedTypeName(String parameterizedTypeName, T handler) {
        return Arrays.stream(handler.getClass().getGenericInterfaces())
                .anyMatch(type -> isParameterizedType(type)
                        && hasParameterizedTypeName(parameterizedTypeName, (ParameterizedType) type));
    }

    private boolean hasParameterizedTypeName(String parameterizedTypeName, ParameterizedType type) {
        return Arrays.stream(type.getActualTypeArguments()).anyMatch(argument -> argument.getTypeName().equals(parameterizedTypeName));
    }

    private boolean isParameterizedType(Type type) {
        return type instanceof ParameterizedType;
    }
}
