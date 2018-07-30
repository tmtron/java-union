package com.tmtron.java.union.internal.gen.functions;

import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

class ClassTypeVariables extends TypeFragment {

    ClassTypeVariables(final Config config) {
        super(config);
    }

    @Override
    void work(int parameterOneBased, final TypeVariableName typeVariableName) {
        config.builder.addTypeVariable(typeVariableName);
    }

    @Override
    void finish() {
        config.builder.addTypeVariable(Util.RESULT_TYPE_VARIABLE);
    }
}
