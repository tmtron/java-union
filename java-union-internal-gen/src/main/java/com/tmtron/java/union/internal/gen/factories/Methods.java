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
package com.tmtron.java.union.internal.gen.factories;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.internal.gen.shared.TypeFragment;
import com.tmtron.java.union.internal.gen.unions.GenUnions;

import javax.lang.model.element.Modifier;

class Methods extends TypeFragment {

    private final GenUnions genUnions;

    Methods(final Config config, final GenUnions genUnions) {
        super(config);
        this.genUnions = genUnions;
    }

    @Override
    public void work(Integer parameterOneBased) {
        final TypeVariableName typeVariableName = config.getTypeVariable(parameterOneBased);
        final MethodSpec.Builder executeSpec = MethodSpec.methodBuilder("element" + parameterOneBased);
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

        ParameterSpec.Builder paramBuilder = ParameterSpec.builder(typeVariableName, paramName);
        executeSpec.addParameter(paramBuilder.build());

        javaDoc.append("\n");

        ClassName returnTypeClassName = genUnions.getGeneratedClassName(config.getNoOfTypeVariables());
        ParameterizedTypeName returnType = ParameterizedTypeName.get(returnTypeClassName, config.getTypeVarArray());
        executeSpec.addJavadoc(javaDoc.toString())
                .returns(returnType)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT);
        config.getBuilder().addMethod(executeSpec.build());

    }
}
