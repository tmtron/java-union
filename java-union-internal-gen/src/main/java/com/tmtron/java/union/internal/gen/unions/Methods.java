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

import com.squareup.javapoet.ParameterSpec;
import com.tmtron.java.union.internal.gen.shared.ExecuteMethodHelper;
import com.tmtron.java.union.internal.gen.shared.TypeFragment;

import javax.lang.model.element.Modifier;

class Methods extends TypeFragment {

    private final ExecuteMethodHelper executeMethodHelper;
    private final StringBuilder javaDoc = new StringBuilder("Executes the corresponding continuation depending on" +
            " the value of the union");

    Methods(final Config config) {
        super(config);
        executeMethodHelper = new ExecuteMethodHelper(config);
    }

    @Override
    public void work(Integer parameterOneBased) {
        ParameterSpec parameterSpec = executeMethodHelper.addParameter(parameterOneBased);

        javaDoc.append("\n@param ");
        javaDoc.append(parameterSpec.name);
        javaDoc.append(" will be executed when it is not null and the value is of type ");
        javaDoc.append(parameterOneBased);
    }

    @Override
    public void finish() {
        javaDoc.append("\n");

        executeMethodHelper.builder().addJavadoc(javaDoc.toString())
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT);
        config.getBuilder().addMethod(executeMethodHelper.builder().build());
    }
}
