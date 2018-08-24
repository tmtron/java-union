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
import com.squareup.javapoet.ParameterizedTypeName;
import com.tmtron.java.union.internal.gen.shared.Nullability;
import com.tmtron.java.union.internal.gen.unions.UnionInterfaceBuilder;
import com.tmtron.javapoet.GenericsInterfaceBuilder;
import com.tmtron.javapoet.TypeVariableInfos;

import javax.lang.model.element.Modifier;

public class UnionFactoryInterfaceBuilder extends GenericsInterfaceBuilder {

    private final UnionInterfaceBuilder unionInterfaceBuilder;
    private final ElementMethodBuilder elementMethodBuilder;

    public UnionFactoryInterfaceBuilder(final ClassName className, final TypeVariableInfos typeVariableInfos,
                                        final Nullability nullability,
                                        final UnionInterfaceBuilder unionInterfaceBuilder) {
        super(className, typeVariableInfos);
        this.unionInterfaceBuilder = unionInterfaceBuilder;

        elementMethodBuilder = createElementMethodBuilder(nullability);

        typeSpecBuilder.addModifiers(Modifier.PUBLIC);
        addInterfaceJavaDoc();
        addElementMethods(typeVariableInfos);
    }

    ElementMethodBuilder getElementMethodBuilder() {
        return elementMethodBuilder;
    }

    private ElementMethodBuilder createElementMethodBuilder(final Nullability nullability) {
        final ParameterizedTypeName elementMethodReturnType =
                typeVariableInfos.getParameterizedType(unionInterfaceBuilder.getClassName());
        return new ElementMethodBuilder(typeVariableInfos, elementMethodReturnType, nullability);
    }

    private void addElementMethods(final TypeVariableInfos typeVariableInfos) {
        typeVariableInfos.forEach(typeVariableInfo ->
                typeSpecBuilder.addMethod(elementMethodBuilder.buildDeclaration(typeVariableInfo))
        );
    }

    private void addInterfaceJavaDoc() {
        typeSpecBuilder.addJavadoc("Factory to create instances of Union" + typeVariableInfos.getNoOfGenericParams() + "\n");

        typeVariableInfos.forEach(typeVariableInfo ->
                typeSpecBuilder.addJavadoc("@param <"
                        + typeVariableInfo.getName() + "> the value of the union element "
                        + typeVariableInfo.getValue() + "\n"
                ));
    }

}
