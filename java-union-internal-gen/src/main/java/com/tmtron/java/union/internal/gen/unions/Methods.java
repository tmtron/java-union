/*
 * Copyright © 2018 Martin Trummer (martin.trummer@tmtron.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tmtron.java.union.internal.gen.unions;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.internal.gen.shared.TypeFragment;
import com.tmtron.java.union.lib.consumers.Consumer;
import com.tmtron.java.union.lib.consumers.ConsumerNullable;

import javax.annotation.Nullable;
import javax.lang.model.element.Modifier;

class Methods extends TypeFragment {

    private final MethodSpec.Builder executeSpec = MethodSpec.methodBuilder("execute");
    private final StringBuilder javaDoc = new StringBuilder("Executes the corresponding continuation depending on" +
            " the value of the union");

    Methods(final Config config) {
        super(config);
    }

    @Override
    public void work(int parameterOneBased) {
        final TypeVariableName typeVariableName = config.getTypeVariable(parameterOneBased);
        final String paramName = "continuation" + parameterOneBased;
        javaDoc.append("\n@param ");
        javaDoc.append(paramName);
        javaDoc.append(" will be executed when it is not null and the value is of type ");
        javaDoc.append(parameterOneBased);

        final Class<?> consumerClass = config.isNullable() ? ConsumerNullable.class : Consumer.class;
        final ClassName consumerClassName = ClassName.get(consumerClass);
        // e.g. Consumer<T1>
        final ParameterizedTypeName paramTypeName = ParameterizedTypeName.get(consumerClassName, typeVariableName);
        final ParameterSpec.Builder paramBuilder = ParameterSpec.builder(paramTypeName, paramName);
        paramBuilder.addAnnotation(Nullable.class);
        executeSpec.addParameter(paramBuilder.build());
    }

    @Override
    public void finish() {
        javaDoc.append("\n");

        executeSpec.addJavadoc(javaDoc.toString())
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT);
        config.getBuilder().addMethod(executeSpec.build());
    }
}
