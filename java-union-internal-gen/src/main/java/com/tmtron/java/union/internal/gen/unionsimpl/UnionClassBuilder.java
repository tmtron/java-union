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

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.tmtron.java.union.internal.gen.shared.EqualsHashToStringBuilder;
import com.tmtron.java.union.internal.gen.shared.Nullability;
import com.tmtron.java.union.lib.j8.ObjectsJ8;
import com.tmtron.javapoet.GenericsClassBuilder;
import com.tmtron.javapoet.TypeVariableInfo;
import com.tmtron.javapoet.TypeVariableInfos;

import javax.lang.model.element.Modifier;

public class UnionClassBuilder extends GenericsClassBuilder {

    private final Nullability nullability;
    private final ClassName unionInterfaceClassName;
    private final int subFileIndex;
    private final TypeVariableInfo typeVariableInfo;

    public UnionClassBuilder(final ClassName className, final TypeVariableInfos typeVariableInfos
            , final Nullability nullability, final ClassName unionInterfaceClassName, final int subFileIndex) {
        super(className, typeVariableInfos);
        this.nullability = nullability;
        this.unionInterfaceClassName = unionInterfaceClassName;
        this.subFileIndex = subFileIndex;

        typeVariableInfo = typeVariableInfos.getTypeVariableInfoByNumber(subFileIndex);

        addClassJavaDoc();
        addUnionInterface();
        final FieldSpec valueFieldSpec = addValueField();
        addConstructor(valueFieldSpec);

        final ExecuteMethodBuilder executeMethodBuilder = new ExecuteMethodBuilder(typeVariableInfos, nullability);
        typeSpecBuilder.addMethod(executeMethodBuilder.buildExecuteMethodImpl(valueFieldSpec, subFileIndex));

        EqualsHashToStringBuilder equalsHashToStringBuilder = new EqualsHashToStringBuilder(className.simpleName(),
                typeVariableInfos.getNoOfGenericParams(), valueFieldSpec.name, nullability);
        equalsHashToStringBuilder.addEqualsAndHashCode(typeSpecBuilder);
        equalsHashToStringBuilder.addToString(typeSpecBuilder);
    }

    private void addConstructor(final FieldSpec valueFieldSpec) {
        ParameterSpec constructorParam = ParameterSpec
                .builder(typeVariableInfo.getName(), valueFieldSpec.name, Modifier.FINAL)
                .addAnnotation(nullability.getNullAnnotationClass())
                .build();
        MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                .addParameter(constructorParam);
        if (nullability == Nullability.NULLABLE) {
            constructorBuilder.addStatement("this.$N = $N", valueFieldSpec, constructorParam);
        } else {
            constructorBuilder.addStatement("this.$1N = $3T.requireNonNull($2N)"
                    , valueFieldSpec, constructorParam, ObjectsJ8.class);
        }
        typeSpecBuilder.addMethod(constructorBuilder.build());
    }

    private FieldSpec addValueField() {
        final FieldSpec valueFieldSpec = FieldSpec
                .builder(typeVariableInfo.getName(), "value", Modifier.PRIVATE, Modifier.FINAL)
                .addAnnotation(nullability.getNullAnnotationClassName())
                .build();
        typeSpecBuilder.addField(valueFieldSpec);
        return valueFieldSpec;
    }

    private void addUnionInterface() {
        ParameterizedTypeName unionInterface = typeVariableInfos.getParameterizedType(unionInterfaceClassName);
        typeSpecBuilder.addSuperinterface(unionInterface);
    }

    private void addClassJavaDoc() {
        typeSpecBuilder.addJavadoc("Union Implementation that has "
                + typeVariableInfos.getNoOfGenericParams() + " different types.\n");

        typeVariableInfos.forEach(typeVariableInfo -> {
            final String trailer = (subFileIndex == typeVariableInfo.getValue()) ? " for this implementation class" :
                    "";
            typeSpecBuilder.addJavadoc("@param <"
                    + typeVariableInfo.getName() + "> type of element"
                    + trailer + "\n");
        });
    }

}
