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
package com.tmtron.java.union.internal.gen.entry.unions;

import com.squareup.javapoet.MethodSpec;
import com.tmtron.java.union.internal.gen.shared.TypeFragment;
import com.tmtron.java.union.internal.gen.shared.Util;

import javax.lang.model.element.Modifier;

class Methods extends TypeFragment {

    Methods(final Config config) {
        super(config);
    }

    @Override
    public void work(Integer parameterOneBased) {
        final String nullableIdentifier = Util.getNullableIdentifierNameOrBlank(config.isNullable());
        String methodName = String.format("factory%d%s", parameterOneBased, nullableIdentifier);
        String javaDoc = String.format("Creates a %s union %d factory", nullableIdentifier, parameterOneBased);
        MethodSpec.Builder executeSpecBuilder = MethodSpec.methodBuilder(methodName)
                .addJavadoc(javaDoc)
                .addModifiers(Modifier.PUBLIC);

        config.getBuilder().addMethod(executeSpecBuilder.build());

    }
}
