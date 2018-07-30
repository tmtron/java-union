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

import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.internal.gen.shared.TypeFragment;

import static com.tmtron.java.union.internal.gen.shared.Util.RESULT_TYPE_VARIABLE;

class JavaDoc extends TypeFragment {

    private final StringBuilder javaDoc = new StringBuilder();

    JavaDoc(final Config config) {
        super(config);
    }

    @Override
    public void prepare() {
        javaDoc.append("A functional interface (callback) that returns a value.");
    }

    @Override
    public void work(int parameterOneBased, final TypeVariableName typeVariableName) {
        javaDoc.append("\n@param <");
        javaDoc.append(typeVariableName.name);
        javaDoc.append("> the value type for parameter ");
        javaDoc.append(parameterOneBased);
    }

    @Override
    public void finish() {
        javaDoc.append("\n@param <");
        javaDoc.append(RESULT_TYPE_VARIABLE.name);
        javaDoc.append("> the result type");
        javaDoc.append("\n");

        config.getBuilder().addJavadoc(javaDoc.toString());
    }
}
