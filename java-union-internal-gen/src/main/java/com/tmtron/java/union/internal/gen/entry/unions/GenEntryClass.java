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
package com.tmtron.java.union.internal.gen.entry.unions;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.internal.gen.factories.GenFactories;
import com.tmtron.java.union.internal.gen.factoriesimpl.factories.GenFactoriesImpl;
import com.tmtron.java.union.internal.gen.shared.FileWriter;
import com.tmtron.java.union.internal.gen.shared.Util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

public class GenEntryClass extends FileWriter {

    private final Map<Boolean, GenFactories> genFactoriesMap = new HashMap<>();
    private final Map<Boolean, GenFactoriesImpl> genFactoriesImplMap = new HashMap<>();

    public GenEntryClass(final Path outputDir) {
        // TODO: refactor unused params isNullable, minIndex
        super(outputDir, true, Util.PACKAGE_NAME_UNIONS_IMPLEMENTATION, 0);
    }

    @Override
    public void writeFiles() throws IOException {
        writeFile(1);
    }

    @Override
    protected TypeSpec getFunctionFileSpec(final int dummy, final boolean isNullable) {
        ClassName className = ClassName.get(packageName, "Unions");
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC);

        typeSpecBuilder.addJavadoc("This is the entry point class to create unions.");

        for (int i = Util.MIN_INDEX_FOR_UNIONS; i < Util.MAX_INDEX_FOR_UNIONS + 1; i++) {
            addFactoryMethod(typeSpecBuilder, i, false);
        }
        for (int i = Util.MIN_INDEX_FOR_UNIONS; i < Util.MAX_INDEX_FOR_UNIONS + 1; i++) {
            addFactoryMethod(typeSpecBuilder, i, true);
        }

        return typeSpecBuilder.build();
    }

    private void addFactoryMethod(final TypeSpec.Builder typeSpecBuilder, final int unionArity,
                                  final boolean isNullable) {
        final String nullableIdentifier = Util.getNullableIdentifierNameOrBlank(isNullable);
        String methodName = String.format("factory%d%s", unionArity, nullableIdentifier);
        String nullableFormatStr = nullableIdentifier.isEmpty() ? "" : nullableIdentifier + " ";
        String javaDoc = String.format("Creates a %sunion %d factory", nullableFormatStr, unionArity);

        final List<TypeVariableName> typeVariables = getTypeVars(unionArity);

        ClassName factoryClassName = genFactoriesMap.get(isNullable).getFactoryClassName(unionArity);

        TypeVariableName[] typeVariablesArray = typeVariables.toArray(new TypeVariableName[0]);
        ParameterizedTypeName parameterizedFactoryType = ParameterizedTypeName.get(factoryClassName,
                typeVariablesArray);

        MethodSpec.Builder executeSpecBuilder = MethodSpec.methodBuilder(methodName)
                .addJavadoc(javaDoc)
                .addTypeVariables(typeVariables)
                .returns(parameterizedFactoryType)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

        ClassName factoryImplClassName = genFactoriesImplMap.get(isNullable).getGeneratedClassName(unionArity);
        executeSpecBuilder.addStatement("return new $T<>()", factoryImplClassName);
        typeSpecBuilder.addMethod(executeSpecBuilder.build());
    }

    public void setFactories(final boolean isNullable, final GenFactories genFactories) {
        genFactoriesMap.put(isNullable, genFactories);
    }

    public void setFactoriesImpl(final boolean isNullable, final GenFactoriesImpl genFactoriesImpl) {
        genFactoriesImplMap.put(isNullable, genFactoriesImpl);
    }

}
