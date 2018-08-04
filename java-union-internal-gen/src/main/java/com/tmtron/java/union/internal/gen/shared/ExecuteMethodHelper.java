package com.tmtron.java.union.internal.gen.shared;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.lib.consumers.Consumer;
import com.tmtron.java.union.lib.consumers.ConsumerNullable;

import javax.annotation.Nullable;
import javax.lang.model.element.Modifier;

public class ExecuteMethodHelper {
    private final MethodSpec.Builder executeSpecBuilder = MethodSpec.methodBuilder("execute")
            .addModifiers(Modifier.PUBLIC);
    private final TypeFragmentBase.Config config;

    public ExecuteMethodHelper(final TypeFragmentBase.Config config) {
        this.config = config;
    }

    public ParameterSpec addParameter(final Integer parameterOneBased) {
        final String paramName = "continuation" + parameterOneBased;
        final TypeVariableName typeVariableName = config.getTypeVariable(parameterOneBased);
        final Class<?> consumerClass = config.isNullable() ? ConsumerNullable.class : Consumer.class;
        final ClassName consumerClassName = ClassName.get(consumerClass);
        // e.g. Consumer<T1>
        final ParameterizedTypeName paramTypeName = ParameterizedTypeName.get(consumerClassName, typeVariableName);
        final ParameterSpec.Builder paramBuilder = ParameterSpec.builder(paramTypeName, paramName);
        paramBuilder.addAnnotation(Nullable.class);

        ParameterSpec result = paramBuilder.build();
        executeSpecBuilder.addParameter(result);
        return result;
    }

    public MethodSpec.Builder builder() {
        return executeSpecBuilder;
    }
}
