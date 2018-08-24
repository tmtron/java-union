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
package com.tmtron.java.union.internal.gen.functions;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.tmtron.java.union.internal.gen.shared.Nullability;
import com.tmtron.java.union.lib.j8.Consumer;
import com.tmtron.javapoet.GenericsInterfaceBuilder;
import com.tmtron.javapoet.TypeVariableInfo;
import com.tmtron.javapoet.TypeVariableInfos;

import javax.annotation.CheckReturnValue;
import javax.lang.model.element.Modifier;

import static com.tmtron.java.union.internal.gen.shared.Util.RESULT_TYPE_VARIABLE;

class FunctionInterfaceBuilder extends GenericsInterfaceBuilder {

    private final Nullability nullability;
    private final StringBuilder methodJavaDoc = new StringBuilder();

    FunctionInterfaceBuilder(final ClassName className, final TypeVariableInfos typeVariableInfos
            , final Nullability nullability) {
        super(className, typeVariableInfos.append(RESULT_TYPE_VARIABLE.name));
        this.nullability = nullability;

        typeSpecBuilder.addModifiers(Modifier.PUBLIC);
        addInterfaceJavaDoc();
        addApplyMethod();
    }

    private void forEachParameter(Consumer<TypeVariableInfo> typeVariableInfoConsumer) {
        typeVariableInfos.forEach(typeVariableInfo -> {
            if (!RESULT_TYPE_VARIABLE.name.equalsIgnoreCase(typeVariableInfo.getName().name)) {
                typeVariableInfoConsumer.accept(typeVariableInfo);
            }
        });
    }

    private void addInterfaceJavaDoc() {
        typeSpecBuilder.addJavadoc("A functional interface (callback) that returns a value.\n");

        forEachParameter(typeVariableInfo ->
                typeSpecBuilder.addJavadoc("@param <"
                        + typeVariableInfo.getName() + "> the value type of element "
                        + typeVariableInfo.getValue() + "\n"
                ));

        typeSpecBuilder.addJavadoc("@param <" + RESULT_TYPE_VARIABLE.name + "> the result type\n");
    }

    private void addApplyMethod() {

        final MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("apply");
        methodBuilder.addModifiers(Modifier.ABSTRACT);
        applyMethodInitJavaDoc(methodBuilder);

        methodBuilder.addException(Exception.class);
        applyMethodAddParameters(methodBuilder);

        methodBuilder.addAnnotation(CheckReturnValue.class);
        methodBuilder.returns(RESULT_TYPE_VARIABLE)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addAnnotation(nullability.getNullAnnotationClass());

        applyMethodFinishJavaDoc(methodBuilder);

        typeSpecBuilder.addMethod(methodBuilder.build());
    }

    private void applyMethodAddParameters(final MethodSpec.Builder methodBuilder) {
        forEachParameter(typeVariableInfo -> {
            final String paramName = "p" + typeVariableInfo.getValue();
            ParameterSpec.Builder paramBuilder = ParameterSpec.builder(typeVariableInfo.getName(), paramName);
            paramBuilder.addAnnotation(nullability.getNullAnnotationClassName());
            methodBuilder.addParameter(paramBuilder.build());

            methodJavaDoc.append("@param ")
                    .append(paramName)
                    .append(" parameter ")
                    .append(typeVariableInfo.getValue())
                    .append("\n");
        });
    }

    private void applyMethodFinishJavaDoc(final MethodSpec.Builder methodBuilder) {
        methodJavaDoc.append("@return the result value\n");
        methodJavaDoc.append("@throws Exception on error\n");
        methodBuilder.addJavadoc(methodJavaDoc.toString());
    }

    private void applyMethodInitJavaDoc(final MethodSpec.Builder methodBuilder) {
        String methodJavaDoc = "returns a value";
        if (typeVariableInfos.getNoOfGenericParams() == 1) {
            methodJavaDoc = methodJavaDoc + " based on the input parameter";
        } else if (typeVariableInfos.getNoOfGenericParams() > 1) {
            methodJavaDoc = methodJavaDoc + " based on the input parameters";
        }
        methodBuilder.addJavadoc(methodJavaDoc + "\n");
    }

}
