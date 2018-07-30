/*
 * Copyright © 2018 Martin Trummer (martin.trummer@tmtron.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tmtron.java.union.internal.gen.shared;

import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

public abstract class TypeFragment {

    public static class Config {
        private final TypeSpec.Builder builder;
        private final int noOfFunctionParams;
        private final boolean throwsException;

        public Config(final TypeSpec.Builder builder, final int noOfFunctionParams, final boolean throwsException) {
            this.builder = builder;
            this.noOfFunctionParams = noOfFunctionParams;
            this.throwsException = throwsException;
        }

        public TypeSpec.Builder getBuilder() {
            return builder;
        }

        public int getNoOfFunctionParams() {
            return noOfFunctionParams;
        }

        public boolean isThrowsException() {
            return throwsException;
        }
    }

    protected final Config config;

    protected TypeFragment(final Config config) {
        this.config = config;
    }

    public void prepare() {}
    public abstract void work(int parameterOneBased, TypeVariableName typeVariableName);
    public abstract void finish();
}
