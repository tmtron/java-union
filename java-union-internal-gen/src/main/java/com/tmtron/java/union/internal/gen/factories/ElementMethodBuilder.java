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
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.internal.gen.shared.Nullability;
import com.tmtron.javapoet.TypeVariableInfo;
import com.tmtron.javapoet.TypeVariableInfos;

import javax.annotation.CheckReturnValue;
import javax.lang.model.element.Modifier;

/**
 * to build the element* methods of the factories
 */
class ElementMethodBuilder {

    private enum Mode {
        API, IMPLEMENTATION
    }

    private final TypeVariableInfos typeVariableInfos;
    private final TypeName returnType;
    private final Nullability nullability;
    private ParameterSpec parameterSpec;

    ElementMethodBuilder(final TypeVariableInfos typeVariableInfos, final TypeName returnType,
                         final Nullability nullability) {
        this.typeVariableInfos = typeVariableInfos;
        this.returnType = returnType;
        this.nullability = nullability;
    }

    private MethodSpec.Builder initMethod(final TypeVariableInfo typeVariableInfo, final Mode mode) {
        final String methodName = "element" + typeVariableInfo.getValue();
        final MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder(methodName);
        methodSpecBuilder.addModifiers(Modifier.PUBLIC);
        if (mode == Mode.API) {
            methodSpecBuilder.addModifiers(Modifier.ABSTRACT);
        } else {
            methodSpecBuilder.addModifiers(Modifier.FINAL)
                    .addAnnotation(CheckReturnValue.class)
                    .addAnnotation(Override.class);
        }

        methodSpecBuilder.returns(returnType);

        addMethodParam(methodSpecBuilder, typeVariableInfo);

        methodSpecBuilder.addJavadoc("@return an instance of Union"
                + typeVariableInfos.getNoOfGenericParams()
                + " for the union element "
                + typeVariableInfo.getValue() + "\n");

        return methodSpecBuilder;
    }

    MethodSpec buildDeclaration(final TypeVariableInfo typeVariableInfo) {
        return initMethod(typeVariableInfo, Mode.API).build();
    }

    MethodSpec buildImplementation(final TypeVariableInfo typeVariableInfo, final ClassName unionImplClassName) {
        MethodSpec.Builder methodBuilder = initMethod(typeVariableInfo, Mode.IMPLEMENTATION);

        addMethodImplementation(methodBuilder, unionImplClassName);
        return methodBuilder.build();
    }

    private void addMethodImplementation(final MethodSpec.Builder methodBuilder, final ClassName unionImplClassName) {
        methodBuilder.addStatement("return new $T<>($N)", unionImplClassName, parameterSpec);
    }

    private ParameterSpec addMethodParam(final MethodSpec.Builder executeSpecBuilder,
                                         final TypeVariableInfo typeVariableInfo) {
        final String paramName = "element" + typeVariableInfo.getValue();
        // e.g. T1
        final TypeVariableName typeVariableName = typeVariableInfo.getName();
        final ParameterSpec.Builder paramBuilder = ParameterSpec.builder(typeVariableName, paramName);
        paramBuilder.addAnnotation(nullability.getNullAnnotationClass());
        ParameterSpec result = paramBuilder.build();
        parameterSpec = result;
        executeSpecBuilder.addParameter(result);

        executeSpecBuilder.addJavadoc("@param " + result.name
                + " the value of the union element "
                + typeVariableInfo.getValue() + "\n");

        return result;
    }

}
