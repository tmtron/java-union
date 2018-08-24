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
import com.squareup.javapoet.TypeName;
import com.tmtron.java.union.internal.gen.unionsimpl.UnionClassBuilder;
import com.tmtron.javapoet.GenericsClassBuilder;
import com.tmtron.javapoet.TypeVariableInfo;
import com.tmtron.javapoet.TypeVariableInfos;

import java.util.List;

public class UnionFactoryImplementationBuilder extends GenericsClassBuilder {

    private final UnionFactoryInterfaceBuilder unionFactoryInterfaceBuilder;
    private final List<UnionClassBuilder> unionClassBuilders;

    public UnionFactoryImplementationBuilder(final ClassName className, final TypeVariableInfos typeVariableInfos
            , final UnionFactoryInterfaceBuilder unionFactoryInterfaceBuilder
            , final List<UnionClassBuilder> unionClassBuilders) {
        super(className, typeVariableInfos);
        this.unionFactoryInterfaceBuilder = unionFactoryInterfaceBuilder;
        this.unionClassBuilders = unionClassBuilders;

        addSuperInterface();
        addInterfaceJavaDoc();
        addElementMethods();
    }

    private void addSuperInterface() {
        final TypeName superInterfaace = typeVariableInfos.getParameterizedType(
                unionFactoryInterfaceBuilder.getClassName());
        typeSpecBuilder.addSuperinterface(superInterfaace);
    }

    private void addElementMethods() {
        final ElementMethodBuilder elementMethodBuilder = unionFactoryInterfaceBuilder.getElementMethodBuilder();
        if (typeVariableInfos.getNoOfGenericParams() != unionClassBuilders.size()) {
            throw new AssertionError("invalid sizes");
        }

        for (int i = 0; i < unionClassBuilders.size(); i++) {
            final UnionClassBuilder unionClassBuilder = unionClassBuilders.get(i);
            final TypeVariableInfo typeVariableInfo = typeVariableInfos.getTypeVariableInfoByIndex(i);
            typeSpecBuilder.addMethod(
                    elementMethodBuilder.buildImplementation(typeVariableInfo, unionClassBuilder.getClassName())
            );
        }
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
