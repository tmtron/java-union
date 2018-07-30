package com.tmtron.java.union.internal.gen.functions;

import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

abstract class TypeFragment {

    static class Config {
        final TypeSpec.Builder builder;
        final int noOfFunctionParams;
        final boolean throwsException;

        Config(final TypeSpec.Builder builder, final int noOfFunctionParams, final boolean throwsException) {
            this.builder = builder;
            this.noOfFunctionParams = noOfFunctionParams;
            this.throwsException = throwsException;
        }
    }

    final Config config;

    TypeFragment(final Config config) {
        this.config = config;
    }

    void prepare() {}
    abstract void work(int parameterOneBased, TypeVariableName typeVariableName);
    abstract void finish();
}
