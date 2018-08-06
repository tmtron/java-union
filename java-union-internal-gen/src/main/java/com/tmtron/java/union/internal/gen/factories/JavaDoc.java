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

import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.internal.gen.shared.TypeFragment;

class JavaDoc extends TypeFragment {

    private final StringBuilder javaDoc = new StringBuilder();

    JavaDoc(final Config config) {
        super(config);
    }

    @Override
    public void prepare() {
        javaDoc.append("Factory to create instances of Union");
        javaDoc.append(config.getNoOfTypeVariables());
    }

    @Override
    public void work(Integer parameterOneBased) {
        final TypeVariableName typeVariableName = config.getTypeVariable(parameterOneBased);
        javaDoc.append("\n@param <");
        javaDoc.append(typeVariableName.name);
        javaDoc.append("> type of element ");
        javaDoc.append(parameterOneBased);
    }

    @Override
    public void finish() {
        javaDoc.append("\n");
        config.getBuilder().addJavadoc(javaDoc.toString());
    }
}
