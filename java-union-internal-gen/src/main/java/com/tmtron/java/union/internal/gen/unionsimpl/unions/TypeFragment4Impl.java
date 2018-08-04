package com.tmtron.java.union.internal.gen.unionsimpl.unions;

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
