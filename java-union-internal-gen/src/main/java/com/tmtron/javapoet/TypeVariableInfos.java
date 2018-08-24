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

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.internal.gen.shared.StringUtil;
import com.tmtron.java.union.lib.j8.Consumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Convenient class to handle {@link TypeVariableInfo}s
 */
public class TypeVariableInfos {

    private static final String DEFAULT_VAR_NAME_PREFIX = "T";
    private final int noOfGenericParams;

    private final List<TypeVariableInfo> typeVariableInfos;

    private TypeVariableInfos(final Collection<String> variableNames) {
        typeVariableInfos = new ArrayList<>(variableNames.size());
        int i = 1;
        for (final String variableName : variableNames) {
            TypeVariableName typeVariableName = TypeVariableName.get(variableName);
            typeVariableInfos.add(new TypeVariableInfo(i++, typeVariableName));
        }
        noOfGenericParams = typeVariableInfos.size();
    }

    public void forEach(Consumer<TypeVariableInfo> typeVariableInfoConsumer) {
        for (TypeVariableInfo typeVariableInfo : typeVariableInfos) {
            typeVariableInfoConsumer.accept(typeVariableInfo);
        }
    }

    public List<TypeVariableName> getTypeVariableNames() {
        List<TypeVariableName> result = new ArrayList<>(typeVariableInfos.size());
        typeVariableInfos.forEach(typeVariableInfo -> result.add(typeVariableInfo.getName()));
        return result;
    }

    public int getNoOfGenericParams() {
        return noOfGenericParams;
    }

    /**
     * Example: getTypeVariableInfoByIndex(0) returns "T1"
     *
     * @param arrayIndexStartsAt0 must be >= 0 and < noOfGenericParams
     * @return the corresponding type variable name
     * @see #getTypeVariableInfoByNumber(int)
     */
    public TypeVariableInfo getTypeVariableInfoByIndex(int arrayIndexStartsAt0) {
        return typeVariableInfos.get(arrayIndexStartsAt0);
    }

    /**
     * Example: getTypeVariableInfoByIndex(1) returns "T1"
     *
     * @param numberStartsAtOne must be >= 1 and <= noOfGenericParams
     * @return the corresponding type variable name
     * @see #getTypeVariableInfoByIndex(int)
     */
    public TypeVariableInfo getTypeVariableInfoByNumber(int numberStartsAtOne) {
        return getTypeVariableInfoByIndex(numberStartsAtOne - 1);
    }

    /**
     * @param noOfGenericParams the number of generic parameters
     * @return a TypeVariableInfos instance with noOfGenericParams called T1, T2, ..
     */
    public static TypeVariableInfos create(final int noOfGenericParams) {
        return create(noOfGenericParams, DEFAULT_VAR_NAME_PREFIX);
    }

    /**
     * @param varNamePrefixInput each variable name will start with this prefix (when the parameter is null
     *                           or blank, {@link #DEFAULT_VAR_NAME_PREFIX} is used
     * @param noOfGenericParams  the number of generic parameters
     * @return a TypeVariableInfos instance with noOfGenericParams called e.g. U1, U2, ..
     */
    public static TypeVariableInfos create(final int noOfGenericParams, String varNamePrefixInput) {
        if (noOfGenericParams < 0)
            throw new IllegalArgumentException("noOfGenericParams must be >= 0");

        final String varNamePrefix = StringUtil.stringOrDefault(varNamePrefixInput, DEFAULT_VAR_NAME_PREFIX);
        final List<String> variableNames = new ArrayList<>(noOfGenericParams);
        for (int i = 1; i <= noOfGenericParams; i++) {
            variableNames.add(varNamePrefix + i);
        }
        return create(variableNames);
    }

    /**
     * @param variableNames the names of the type parameter variables
     * @return a TypeVariableInfos instance with the given parameter variable names: e.g T1, T2, R
     */
    public static TypeVariableInfos create(final Collection<String> variableNames) {
        return new TypeVariableInfos(variableNames);
    }

    private TypeName[] getTypeNamesArr() {
        final TypeName[] result = new TypeName[noOfGenericParams];
        for (int i = 0; i < noOfGenericParams; i++) {
            result[i] = getTypeVariableInfoByIndex(i).getName();
        }
        return result;
    }

    public ParameterizedTypeName getParameterizedType(final ClassName className) {
        return ParameterizedTypeName.get(className, getTypeNamesArr());
    }

    public TypeVariableInfos append(final String lastTypeVariableInfo) {
        final List<String> typeVariableNames = new ArrayList<>(typeVariableInfos.size() + 1);
        typeVariableInfos.forEach(typeVariableInfo -> typeVariableNames.add(typeVariableInfo.getName().name));
        typeVariableNames.add(lastTypeVariableInfo);
        return create(typeVariableNames);
    }
}
