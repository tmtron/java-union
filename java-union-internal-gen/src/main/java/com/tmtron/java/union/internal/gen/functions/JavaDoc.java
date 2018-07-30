package com.tmtron.java.union.internal.gen.functions;

import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import static com.tmtron.java.union.internal.gen.functions.Util.RESULT_TYPE_VARIABLE;

class JavaDoc extends TypeFragment {

    private final StringBuilder javaDoc = new StringBuilder();

    JavaDoc(final Config config) {
        super(config);
    }

    @Override
    void prepare() {
        javaDoc.append("A functional interface (callback) that returns a value.");
    }

    @Override
    void work(int parameterOneBased, final TypeVariableName typeVariableName) {
        javaDoc.append("\n@param <");
        javaDoc.append(typeVariableName.name);
        javaDoc.append("> the value type for parameter ");
        javaDoc.append(parameterOneBased);
    }

    @Override
    void finish() {
        javaDoc.append("\n@param <");
        javaDoc.append(RESULT_TYPE_VARIABLE.name);
        javaDoc.append("> the result type");
        javaDoc.append("\n");

        config.builder.addJavadoc(javaDoc.toString());
    }
}
