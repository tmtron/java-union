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
package com.tmtron.java.union.internal.gen.shared;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

public class EqualsHashToStringBuilder {
    private final MethodSpec.Builder equalsMethodSpec = MethodSpec.methodBuilder("equals");
    private final String simpleClassName;
    private final int noOfTypeVariables;
    private final String fieldName;
    private final Nullability nullability;

    /**
     * @param simpleClassName e.g. Union2Impl1
     * @param noOfTypeVariables needed for casts in the equals method
     * @param fieldName e.g. userName
     * @param nullability if the field is nullable or not
     */
    public EqualsHashToStringBuilder(final String simpleClassName, final int noOfTypeVariables, final String fieldName,
                                     final Nullability nullability) {
        this.simpleClassName = simpleClassName;
        this.noOfTypeVariables = noOfTypeVariables;
        this.fieldName = fieldName;
        this.nullability = nullability;
    }

    public void addEqualsAndHashCode(final TypeSpec.Builder builder) {
        builder.addMethod(buildEquals());
        builder.addMethod(buildHashCode());
    }

    public void addToString(final TypeSpec.Builder builder) {
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
        MethodSpec.Builder hashCodeBuilder = MethodSpec.methodBuilder("hashCode")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(int.class);

        final String returnStatement = (nullability == Nullability.NULLABLE)
                ? "return $1N != null ? $1N.hashCode() : 0"
                : "return $1N.hashCode()";
        hashCodeBuilder.addStatement(returnStatement, fieldName);

        return hashCodeBuilder.build();
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
        equalsMethodSpec.addStatement("final $1L that = ($1L) o", sb.toString());
        final String returnStatement = (nullability == Nullability.NULLABLE)
                ? "return $1L != null ? $1L.equals(that.$1L) : that.$1L == null"
                : "return $1L.equals(that.$1L)";
        equalsMethodSpec.addStatement(returnStatement, fieldName);
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
