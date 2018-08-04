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
package com.tmtron.java.union.internal.gen.functions;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.internal.gen.shared.TypeFragment;

import javax.annotation.CheckReturnValue;
import javax.lang.model.element.Modifier;

import static com.tmtron.java.union.internal.gen.shared.Util.RESULT_TYPE_VARIABLE;

class ApplyMethod extends TypeFragment {

    private final MethodSpec.Builder methodSpec = MethodSpec.methodBuilder("apply");
    private final StringBuilder javaDoc = new StringBuilder("returns a value");

    ApplyMethod(final Config config) {
        super(config);
    }

    @Override
    public void prepare() {
        if (config.getNoOfTypeVariables() == 1) {
            javaDoc.append(" based on the input parameter");
        } else if (config.getNoOfTypeVariables() > 1) {
            javaDoc.append(" based on the input parameters");
        }
    }

    @Override
    public void work(Integer parameterOneBased) {
        final String paramName = "p"+parameterOneBased;
        javaDoc.append("\n@param ");
        javaDoc.append(paramName);
        javaDoc.append(" parameter ");
        javaDoc.append(parameterOneBased);

        final TypeVariableName typeVariableName = config.getTypeVariable(parameterOneBased);

        ParameterSpec.Builder paramBuilder = ParameterSpec.builder(typeVariableName, paramName);
        addNullAnnotation(paramBuilder);
        methodSpec.addParameter(paramBuilder.build());
    }

    @Override
    public void finish() {
        javaDoc.append("\n@return the result value");
        javaDoc.append("\n@throws Exception on error");

        methodSpec.addException(Exception.class);
        javaDoc.append("\n");

        methodSpec.addJavadoc(javaDoc.toString());
        methodSpec.addAnnotation(CheckReturnValue.class);
        methodSpec.returns(RESULT_TYPE_VARIABLE)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addAnnotation(config.getNullAnnotationClass());
        config.getBuilder().addMethod(methodSpec.build());
    }
}
