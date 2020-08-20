package com.db1group.buster.quarkus.commom;

import com.db1group.buster.quarkus.common.ClientProxySpecification;
import io.quarkus.arc.ClientProxy;
import io.quarkus.arc.InjectableBean;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientProxySpecificationTest {

    private final ClientProxy clientProxy = mock(ClientProxy.class);

    private final InjectableBean injectableBean = mock(InjectableBean.class);

    private final ParameterizedType parameterizedType = mock(ParameterizedType.class);

    private final Type type = mock(Type.class);

    @Test
    void hasParameterizedTypeName_whenExists_shouldReturnTrue() {
        String typeName = "customType";

        when(clientProxy.arc_bean()).thenReturn(injectableBean);
        when(injectableBean.getTypes()).thenReturn(Sets.newSet(parameterizedType));
        when(parameterizedType.getActualTypeArguments()).thenReturn(new Type[]{type});
        when(type.getTypeName()).thenReturn(typeName);

        boolean result = ClientProxySpecification.hasParameterizedTypeName(clientProxy, typeName);
        assertTrue(result);
    }

    @Test
    void hasParameterizedTypeName_whenNotHasTheName_shouldReturnFalse() {
        String typeName = "customType";

        when(clientProxy.arc_bean()).thenReturn(injectableBean);
        when(injectableBean.getTypes()).thenReturn(Sets.newSet(parameterizedType));
        when(parameterizedType.getActualTypeArguments()).thenReturn(new Type[]{type});
        when(type.getTypeName()).thenReturn(typeName);

        boolean result = ClientProxySpecification.hasParameterizedTypeName(clientProxy, "otherName");
        assertFalse(result);
    }

    @Test
    void hasParameterizedTypeName_whenNotExistsParameterizedType_shouldReturnFalse() {
        String typeName = "customType";
        when(clientProxy.arc_bean()).thenReturn(injectableBean);
        when(injectableBean.getTypes()).thenReturn(Sets.newSet());

        boolean result = ClientProxySpecification.hasParameterizedTypeName(clientProxy, typeName);
        assertFalse(result);
    }
}