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
package com.tmtron.javapoet;

import com.squareup.javapoet.TypeVariableName;

/**
 * Simple value class for the number and full name of a generic type parameter
 */
public class TypeVariableInfo {
    private final int typeVariableValue;
    private final TypeVariableName typeVariableName;

    /**
     * @param typeVariableValue e.g. 2
     * @param typeVariableName  e.g. "T2"
     */
    TypeVariableInfo(final int typeVariableValue, final TypeVariableName typeVariableName) {
        this.typeVariableValue = typeVariableValue;
        this.typeVariableName = typeVariableName;
    }

    public int getValue() {
        return typeVariableValue;
    }

    public TypeVariableName getName() {
        return typeVariableName;
    }
}
