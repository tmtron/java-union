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
package com.tmtron.java.union.internal.gen.factoriesimpl.factories;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.internal.gen.shared.TypeFragment;
import com.tmtron.java.union.internal.gen.unions.GenUnions;
import com.tmtron.java.union.internal.gen.unionsimpl.GenUnionsImpl;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.lang.model.element.Modifier;

class Methods extends TypeFragment {

    private final GenUnions genUnions;
    private final GenUnionsImpl genUnionsImpl;

    Methods(final Config config, final GenUnions genUnions, final GenUnionsImpl genUnionsImpl) {
        super(config);
        this.genUnions = genUnions;
        this.genUnionsImpl = genUnionsImpl;
    }

    @Override
    public void work(Integer parameterOneBased) {
        final TypeVariableName typeVariableName = config.getTypeVariable(parameterOneBased);
        final MethodSpec.Builder executeSpec = MethodSpec
                .methodBuilder("element" + parameterOneBased)
                .addAnnotation(CheckReturnValue.class);

        final StringBuilder javaDoc = new StringBuilder();

        javaDoc.append("Creates an instance of Union");
        javaDoc.append(config.getNoOfTypeVariables());
        javaDoc.append(" for the union element ");
        javaDoc.append(parameterOneBased);
        javaDoc.append("\n");

        final String paramName = "element" + parameterOneBased;
        javaDoc.append("\n@param ");
        javaDoc.append(paramName);
        javaDoc.append(" the value of the union element ");
        javaDoc.append(parameterOneBased);

        ParameterSpec.Builder paramBuilder = ParameterSpec.builder(typeVariableName, paramName)
                .addAnnotation(Nonnull.class);
        ParameterSpec valueParam = paramBuilder.build();
        executeSpec.addParameter(valueParam);

        javaDoc.append("\n");

        ClassName returnTypeClassName = genUnions.getGeneratedClassName(config.getNoOfTypeVariables());
        ParameterizedTypeName returnType = ParameterizedTypeName.get(returnTypeClassName, config.getTypeVarArray());
        executeSpec.addJavadoc(javaDoc.toString())
                .returns(returnType)
                .addModifiers(Modifier.PUBLIC);

        ClassName unionImplClassName = genUnionsImpl.getGeneratedClassName(config.getNoOfTypeVariables(),
                parameterOneBased);
        executeSpec.addStatement("return new $T<>($N)", unionImplClassName, valueParam);

        config.getBuilder().addMethod(executeSpec.build());

    }
}
