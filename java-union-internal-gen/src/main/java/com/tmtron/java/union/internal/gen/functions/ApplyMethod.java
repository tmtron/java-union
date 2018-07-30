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
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import javax.lang.model.element.Modifier;

import static com.tmtron.java.union.internal.gen.functions.Util.RESULT_TYPE_VARIABLE;

class ApplyMethod extends TypeFragment {

    private final MethodSpec.Builder methodSpec = MethodSpec.methodBuilder("apply");
    private final StringBuilder javaDoc = new StringBuilder("returns a value");

    ApplyMethod(final Config config) {
        super(config);
    }

    @Override
    void prepare() {
        if (config.noOfFunctionParams == 1) {
            javaDoc.append(" based on the input parameter");
        } else if (config.noOfFunctionParams > 1) {
            javaDoc.append(" based on the input parameters");
        }
    }

    @Override
    void work(int parameterOneBased, final TypeVariableName typeVariableName) {
        final String paramName = "p"+parameterOneBased;
        javaDoc.append("\n@param ");
        javaDoc.append(paramName);
        javaDoc.append(" parameter ");
        javaDoc.append(parameterOneBased);

        methodSpec.addParameter(typeVariableName, paramName);
    }

    @Override
    void finish() {
        javaDoc.append("\n@return the result value");

        if (config.throwsException) {
            javaDoc.append("\n@throws Exception on error");

            methodSpec.addException(Exception.class);
        }
        javaDoc.append("\n");

        methodSpec.addJavadoc(javaDoc.toString());
        methodSpec.returns(RESULT_TYPE_VARIABLE)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT);
        config.builder.addMethod(methodSpec.build());
    }
}