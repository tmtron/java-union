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
package com.tmtron.java.union.internal.gen.unions;

import com.squareup.javapoet.ClassName;
import com.tmtron.java.union.internal.gen.shared.Nullability;
import com.tmtron.java.union.internal.gen.unionsimpl.ExecuteMethodBuilder;
import com.tmtron.javapoet.GenericsInterfaceBuilder;
import com.tmtron.javapoet.TypeVariableInfos;

import javax.lang.model.element.Modifier;

public class UnionInterfaceBuilder extends GenericsInterfaceBuilder {

    public UnionInterfaceBuilder(final ClassName className, final TypeVariableInfos typeVariableInfos,
                                 final Nullability nullability) {
        super(className, typeVariableInfos);

        typeSpecBuilder.addModifiers(Modifier.PUBLIC);
        addInterfaceJavaDoc();
        addExecuteMethod(typeVariableInfos, nullability);
    }

    private void addExecuteMethod(final TypeVariableInfos typeVariableInfos, final Nullability nullability) {
        final ExecuteMethodBuilder executeMethodBuilder = new ExecuteMethodBuilder(typeVariableInfos, nullability);
        typeSpecBuilder.addMethod(executeMethodBuilder.buildExecuteMethod());
    }

    private void addInterfaceJavaDoc() {
        typeSpecBuilder.addJavadoc("This Union can have " + typeVariableInfos.getNoOfGenericParams() + " different " +
                "types.\n");

        typeVariableInfos.forEach(typeVariableInfo ->
                typeSpecBuilder.addJavadoc("@param <"
                        + typeVariableInfo.getName() + "> type of element "
                        + typeVariableInfo.getValue() + "\n"
                ));
    }

}
