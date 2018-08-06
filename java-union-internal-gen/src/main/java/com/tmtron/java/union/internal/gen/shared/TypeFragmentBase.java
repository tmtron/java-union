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

import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class TypeFragmentBase<T> {

    public static class Config {
        private final TypeSpec.Builder builder;
        private final int noOfTypeVariables;
        private final boolean isNullable;
        private final TypeVariableName[] typeVariableArray;

        public Config(final TypeSpec.Builder builder, final int noOfTypeVariables, final boolean isNullable) {
            this.builder = builder;
            this.noOfTypeVariables = noOfTypeVariables;
            this.isNullable = isNullable;
            typeVariableArray = initTypeVariables();
        }

        private TypeVariableName[] initTypeVariables() {
            final List<TypeVariableName> typeVariables = new ArrayList<>();
            for (int i = 1; i < noOfTypeVariables + 1; i++) {
                TypeVariableName typeVariableName = TypeVariableName.get("T" + i);
                typeVariables.add(typeVariableName);
            }
            return typeVariables.toArray(new TypeVariableName[0]);
        }

        public TypeSpec.Builder getBuilder() {
            return builder;
        }

        public int getNoOfTypeVariables() {
            return noOfTypeVariables;
        }

        public boolean isNullable() {
            return isNullable;
        }

        public Class<?> getNullAnnotationClass() {
            return isNullable ? Nullable.class : Nonnull.class;
        }

        public TypeVariableName[] getTypeVarArray() {
            return typeVariableArray;
        }

        public TypeVariableName getTypeVariable(int paramOneBased) {
            return typeVariableArray[paramOneBased - 1];
        }
    }

    protected final Config config;

    protected TypeFragmentBase(final Config config) {
        this.config = config;
    }

    public void prepare() {}

    public abstract void work(T context);

    protected void addNullAnnotation(final ParameterSpec.Builder paramBuilder) {
        paramBuilder.addAnnotation(config.getNullAnnotationClass());
    }

    public void finish() {
    }
}
