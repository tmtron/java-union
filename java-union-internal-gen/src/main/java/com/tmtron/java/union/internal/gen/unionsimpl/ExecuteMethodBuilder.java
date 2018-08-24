//
// Copyright © 2018 Martin Trummer (martin.trummer@tmtron.com)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
package com.tmtron.java.union.internal.gen.unionsimpl;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.internal.gen.shared.Nullability;
import com.tmtron.javapoet.TypeVariableInfo;
import com.tmtron.javapoet.TypeVariableInfos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.lang.model.element.Modifier;

/**
 * to build a method with generic type parameters
 */
public class ExecuteMethodBuilder {

    private enum Mode {
        API, IMPLEMENTATION
    }

    private final MethodSpec.Builder executeSpecBuilder;
    private final TypeVariableInfos typeVariableInfos;
    private final Nullability nullability;
    private final List<ParameterSpec> parameterSpecs = new ArrayList<>();

    public ExecuteMethodBuilder(final TypeVariableInfos typeVariableInfos, final Nullability nullability) {
        this.nullability = nullability;
        this.executeSpecBuilder = MethodSpec.methodBuilder("execute");
        this.typeVariableInfos = typeVariableInfos;
    }

    private MethodSpec.Builder initExecuteMethod(final Mode mode) {
        executeSpecBuilder.addModifiers(Modifier.PUBLIC);
        if (mode == Mode.API) {
            executeSpecBuilder.addModifiers(Modifier.ABSTRACT);
        } else {
            executeSpecBuilder.addModifiers(Modifier.FINAL)
                    .addAnnotation(Override.class);
        }

        executeSpecBuilder.addJavadoc("Executes the corresponding continuation depending on " +
                "the value of the union\n");
        typeVariableInfos.forEach(typeVariableInfo -> addExecuteMethodParam(executeSpecBuilder, typeVariableInfo));

        return executeSpecBuilder;
    }

    public MethodSpec buildExecuteMethod() {
        return initExecuteMethod(Mode.API).build();
    }

    MethodSpec buildExecuteMethodImpl(final FieldSpec valueFieldSpec, final int subFileIndex) {
        MethodSpec.Builder methodBuilder = initExecuteMethod(Mode.IMPLEMENTATION);

        final ParameterSpec continuationToExecute = parameterSpecs.get(subFileIndex - 1);
        addMethodImplementation(methodBuilder, valueFieldSpec, continuationToExecute);
        return methodBuilder.build();
    }

    private void addMethodImplementation(final MethodSpec.Builder methodBuilder, final FieldSpec valueFieldSpec,
                                         final ParameterSpec consumerToCall) {
        methodBuilder.beginControlFlow("try")
                .beginControlFlow("if ($N != null)", consumerToCall)
                .addStatement("$N.accept($N)", consumerToCall, valueFieldSpec)
                .endControlFlow()
                .nextControlFlow("catch ($T e)", Exception.class)
                .addStatement("throw new $T(e)", RuntimeException.class)
                .endControlFlow();
    }

    private ParameterSpec addExecuteMethodParam(final MethodSpec.Builder executeSpecBuilder,
                                                final TypeVariableInfo typeVariableInfo) {
        final String paramName = "continuation" + typeVariableInfo.getValue();
        final TypeVariableName typeVariableName = typeVariableInfo.getName();
        // e.g. Consumer<T1>
        final ParameterizedTypeName paramTypeName = ParameterizedTypeName.get(nullability.getConsumerClassName()
                , typeVariableName);
        final ParameterSpec.Builder paramBuilder = ParameterSpec.builder(paramTypeName, paramName);
        paramBuilder.addAnnotation(Nullable.class);

        ParameterSpec result = paramBuilder.build();
        parameterSpecs.add(result);
        executeSpecBuilder.addParameter(result);

        executeSpecBuilder.addJavadoc("@param " + result.name
                + " will be executed when it is not null and the value is of type "
                + typeVariableInfo.getValue() + "\n");

        return result;
    }

}
