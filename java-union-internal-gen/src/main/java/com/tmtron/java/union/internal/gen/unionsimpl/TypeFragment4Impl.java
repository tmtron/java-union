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

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.tmtron.java.union.internal.gen.shared.TypeFragment;
import com.tmtron.java.union.internal.gen.shared.TypeFragmentBase;
import com.tmtron.java.union.internal.gen.unions.GenUnions;

abstract class TypeFragment4Impl extends TypeFragmentBase<ImplWorkParams> {

    final TypeFragment4Impl.ImplConfig implConfig;

    // e.g. Union3<T1, T2, T3>
    protected ParameterizedTypeName getUnionInterfaceType() {
        ClassName unionInterfaceClassName = implConfig.genUnions.getGeneratedClassName(config.getNoOfTypeVariables());
        return ParameterizedTypeName.get(unionInterfaceClassName, config.getTypeVarArray());
    }

    static class ImplConfig {
        final ClassName className;
        final GenUnions genUnions;
        final TypeFragment.Config config;

        ImplConfig(final ClassName className, final GenUnions genUnions, final Config config) {
            this.className = className;
            this.genUnions = genUnions;
            this.config = config;
        }
    }

    TypeFragment4Impl(final ImplConfig implConfig) {
        super(implConfig.config);
        this.implConfig = implConfig;
    }
}
