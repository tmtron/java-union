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
import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.internal.gen.shared.EqualsHashCodeHelper;
import com.tmtron.java.union.internal.gen.shared.ExecuteMethodHelper;
import com.tmtron.java.union.lib.j8.ObjectsJ8;

import javax.lang.model.element.Modifier;

class Methods extends TypeFragment4Impl {

    private final ExecuteMethodHelper executeMethodHelper;
    private FieldSpec valueFieldSpec;

    Methods(final ImplConfig implConfig) {
        super(implConfig);
        executeMethodHelper = new ExecuteMethodHelper(config);
    }

    @Override
    public void work(final ImplWorkParams context) {
        ParameterSpec parameterSpec = executeMethodHelper.addParameter(context.implementationIndex);
        final TypeVariableName typeVariableName = config.getTypeVariable(context.unionIndex);

        if (context.implementationIndex == context.unionIndex) {
            valueFieldSpec = FieldSpec
                    .builder(typeVariableName, "value", Modifier.PRIVATE, Modifier.FINAL)
                    .addAnnotation(config.getNullAnnotationClass())
                    .build();

            constructor(typeVariableName);

            executeMethodHelper.builder()
                    .beginControlFlow("try")
                    .beginControlFlow("if ($N != null)", parameterSpec)
                    .addStatement("$N.accept($N)", parameterSpec, valueFieldSpec)
                    .endControlFlow()
                    .nextControlFlow("catch ($T e)", Exception.class)
                    .addStatement("throw new $T(e)", RuntimeException.class)
                    .endControlFlow();
        }
    }

    @Override
    public void finish() {
        equalsAndHashCode();
        executeMethodHelper.builder()
                .addModifiers(Modifier.FINAL)
                .addAnnotation(Override.class);
        config.getBuilder().addMethod(executeMethodHelper.builder().build());

        config.getBuilder().addField(valueFieldSpec);
    }

    private void constructor(TypeVariableName valueParamTypeName) {
        ParameterSpec constructorParam = ParameterSpec
                .builder(valueParamTypeName, valueFieldSpec.name, Modifier.FINAL)
                .addAnnotation(config.getNullAnnotationClass())
                .build();
        MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                .addParameter(constructorParam);
        if (config.isNullable()) {
            constructorBuilder.addStatement("this.$N = $N", valueFieldSpec, constructorParam);
        } else {
            constructorBuilder.addStatement("this.$1N = $3T.requireNonNull($2N)"
                    , valueFieldSpec, constructorParam, ObjectsJ8.class);
        }
        config.getBuilder().addMethod(constructorBuilder.build());
    }

    private void equalsAndHashCode() {
        final EqualsHashCodeHelper helper = new EqualsHashCodeHelper(implConfig.className.simpleName()
                , config.getNoOfTypeVariables()
                , valueFieldSpec.name, config.isNullable());
        helper.addEqualsAndHashCode(config.getBuilder());
    }
}
