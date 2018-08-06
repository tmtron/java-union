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

import com.squareup.javapoet.TypeVariableName;

class JavaDoc extends TypeFragment4Impl {

    private final StringBuilder javaDoc = new StringBuilder();

    JavaDoc(final ImplConfig implConfig) {
        super(implConfig);
    }

    @Override
    public void prepare() {
        javaDoc.append("Implementation for Union");
        javaDoc.append(config.getNoOfTypeVariables());
    }

    @Override
    public void work(final ImplWorkParams context) {
        final TypeVariableName typeVariableName = config.getTypeVariable(context.implementationIndex);
        javaDoc.append("\n@param <");
        javaDoc.append(typeVariableName.name);
        javaDoc.append("> type of element ");
        javaDoc.append(context.implementationIndex);
        if (context.implementationIndex == context.unionIndex) {
            javaDoc.append(" for this implementation class ");
        }
    }

    @Override
    public void finish() {
        javaDoc.append("\n");
        config.getBuilder().addJavadoc(javaDoc.toString());
    }
}
