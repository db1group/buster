package com.db1group.buster.quarkus.common;

import io.quarkus.arc.ClientProxy;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.stream.Stream;

public final class ClientProxySpecification {

    public static boolean hasParameterizedTypeName(ClientProxy proxy, String typeName) {
        return proxy
                .arc_bean()
                .getTypes()
                .stream()
                .anyMatch(type -> isParameterizedType(type) && hasTypeNameAsArgument(typeName, (ParameterizedType) type));
    }

    private static boolean isParameterizedType(Type type) {
        return type instanceof ParameterizedType;
    }

    private static boolean hasTypeNameAsArgument(String typeName, ParameterizedType type) {
        return Stream.of(type.getActualTypeArguments()).anyMatch(parameterizedType -> parameterizedType.getTypeName().equals(typeName));
    }
}
