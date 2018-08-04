package com.tmtron.java.union.internal.gen.shared;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

public class EqualsHashCodeHelper {
    private final MethodSpec.Builder equalsMethodSpec = MethodSpec.methodBuilder("equals");
    private final String simpleClassName;
    private final int noOfTypeVariables;
    private final String fieldName;

    /**
     * @param simpleClassName e.g. Union2Impl1
     * @param noOfTypeVariables needed for casts in the equals method
     * @param fieldName e.g. userName
     */
    public EqualsHashCodeHelper(final String simpleClassName, final int noOfTypeVariables, final String fieldName) {
        this.simpleClassName = simpleClassName;
        this.noOfTypeVariables = noOfTypeVariables;
        this.fieldName = fieldName;
    }

    public void addEqualsAndHashCode(final TypeSpec.Builder builder) {
        builder.addMethod(buildEquals());
        builder.addMethod(buildHashCode());
        builder.addMethod(buildToString());
    }

    private MethodSpec buildToString() {
        return MethodSpec.methodBuilder("toString")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(String.class)
                .addStatement("return \"$L{value=\"+value+\"}\"", simpleClassName)
                .build();
    }

    private MethodSpec buildHashCode() {
        return MethodSpec.methodBuilder("hashCode")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(int.class)
                .addStatement("return $1N != null ? $1N.hashCode() : 0", fieldName)
                .build();
    }

    private void equalsSelfCheck(ParameterSpec otherObject) {
        equalsMethodSpec
                .beginControlFlow("if (this == $N)", otherObject)
                .addStatement("return true")
                .endControlFlow();
    }

    private void equalsOtherObjectCheck(ParameterSpec otherObject) {
        equalsMethodSpec
                .beginControlFlow("if ($1N == null || getClass() != $1N.getClass() )", otherObject)
                .addStatement("return false")
                .endControlFlow();
    }

    private void equalsContentCheck(ParameterSpec otherObject) {
        // build Union2Impl1<?, ?>
        final StringBuilder sb = new StringBuilder(simpleClassName);
        sb.append("<");
        for (int i = 1; i <= noOfTypeVariables; i++) {
            if (i > 1) sb.append(", ");
            sb.append("?");
        }
        sb.append(">");
        equalsMethodSpec
                .addStatement("final $1L that = ($1L) o", sb.toString())
                .addStatement("return $1L != null ? $1L.equals(that.$1L) : that.$1L == null", fieldName);
    }

    private MethodSpec buildEquals() {
        ParameterSpec otherObject = ParameterSpec
                .builder(Object.class, "o", Modifier.FINAL)
                .build();
        equalsMethodSpec
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(boolean.class)
                .addParameter(otherObject);

        equalsSelfCheck(otherObject);
        equalsOtherObjectCheck(otherObject);

        equalsContentCheck(otherObject);

        return equalsMethodSpec.build();
    }

}
