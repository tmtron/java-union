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
package com.tmtron.java.union.internal.gen;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.tmtron.java.union.internal.gen.factories.UnionFactoryImplementationBuilder;
import com.tmtron.java.union.internal.gen.factories.UnionFactoryInterfaceBuilder;
import com.tmtron.java.union.internal.gen.shared.FileSpecWriter;
import com.tmtron.java.union.internal.gen.shared.Nullability;
import com.tmtron.java.union.internal.gen.shared.Util;
import com.tmtron.java.union.internal.gen.unions.UnionInterfaceBuilder;
import com.tmtron.java.union.internal.gen.unionsimpl.UnionClassBuilder;
import com.tmtron.javapoet.GenericsTypeSpecBuilder;
import com.tmtron.javapoet.TypeVariableInfos;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

/**
 * generates files for the given union arity: union interface/implementation, union factory interface/implementation
 */
class UnionGenerator {

    private final Path outputDir;
    private final Nullability nullability;
    private final int unionFileIndex;

    private TypeVariableInfos typeVariableInfos;
    private UnionInterfaceBuilder unionInterfaceBuilder;
    private final List<UnionClassBuilder> unionClassBuilders = new ArrayList<>();
    private UnionFactoryInterfaceBuilder factoryInterfaceBuilder;
    private UnionFactoryImplementationBuilder factoryImplementationBuilder;

    UnionGenerator(final Path outputDir, final Nullability nullability, final int unionFileIndex) {
        this.outputDir = outputDir;
        this.nullability = nullability;
        this.unionFileIndex = unionFileIndex;
    }

    void work(final UnionsClassBuilder unionsClassBuilder) throws IOException {
        typeVariableInfos = TypeVariableInfos.create(unionFileIndex);

        buildUnionInterfaceFile();
        buildUnionImplementationFiles();
        buildFactoryInterfaceFile();
        buildFactoryImplementationFile();

        addFactoryMethod(unionsClassBuilder);
    }

    private void addFactoryMethod(final UnionsClassBuilder unionsClassBuilder) {
        TypeSpec.Builder unionsTypeSpecBuilder = unionsClassBuilder.getTypeSpecBuilder();

        final String methodName = "factory" + typeVariableInfos.getNoOfGenericParams()
                + nullability.getNullableIdentifierNameOrBlank();
        final MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder(methodName);
        methodSpecBuilder.addModifiers(Modifier.PUBLIC, Modifier.STATIC);

        final String nullableIdentifier = nullability.getNullableIdentifierNameOrBlank();
        String nullableFormatStr = nullableIdentifier.isEmpty() ? "" : nullableIdentifier + " ";
        String javaDoc = String.format("Creates a %sunion %d factory", nullableFormatStr,
                typeVariableInfos.getNoOfGenericParams());
        methodSpecBuilder.addJavadoc(javaDoc);

        methodSpecBuilder.addTypeVariables(typeVariableInfos.getTypeVariableNames());
        ParameterizedTypeName factoryInterfaceType =
                typeVariableInfos.getParameterizedType(factoryInterfaceBuilder.getClassName());
        methodSpecBuilder.returns(factoryInterfaceType);

        methodSpecBuilder.addStatement("return new $T<>()", factoryImplementationBuilder.getClassName());
        unionsTypeSpecBuilder.addMethod(methodSpecBuilder.build());
    }

    private void buildUnionInterfaceFile() throws IOException {
        final String interfaceName = "Union" + unionFileIndex + nullability.getNullableIdentifierNameOrBlank();
        ClassName className = ClassName.get(Util.PACKAGE_NAME_ROOT, interfaceName);
        unionInterfaceBuilder = new UnionInterfaceBuilder(className, typeVariableInfos, nullability);
        writeFileSpec(unionInterfaceBuilder);
    }

    private void buildUnionImplementationFiles() throws IOException {
        final ClassName unionInterfaceClassName = unionInterfaceBuilder.getClassName();
        for (int implFileIndex = 1; implFileIndex <= unionFileIndex; implFileIndex++) {

            final String classNameString = "Union" + unionFileIndex + nullability.getNullableIdentifierNameOrBlank()
                    + "Imp" + implFileIndex;
            ClassName className = ClassName.get(Util.PACKAGE_NAME_IMPLEMENTATION, classNameString);

            UnionClassBuilder unionClassBuilder = new UnionClassBuilder(className
                    , typeVariableInfos, nullability, unionInterfaceClassName, implFileIndex);
            writeFileSpec(unionClassBuilder);
            unionClassBuilders.add(unionClassBuilder);
        }
    }

    private void buildFactoryInterfaceFile() throws IOException {
        final String interfaceName =
                "Union" + unionFileIndex + "Factory" + nullability.getNullableIdentifierNameOrBlank();
        ClassName className = ClassName.get(Util.PACKAGE_NAME_FACTORIES, interfaceName);
        factoryInterfaceBuilder = new UnionFactoryInterfaceBuilder(className, typeVariableInfos, nullability,
                unionInterfaceBuilder);
        writeFileSpec(factoryInterfaceBuilder);
    }

    private void buildFactoryImplementationFile() throws IOException {
        final String interfaceName =
                "Union" + unionFileIndex + "FactoryImp" + nullability.getNullableIdentifierNameOrBlank();
        ClassName className = ClassName.get(Util.PACKAGE_NAME_IMPLEMENTATION, interfaceName);
        factoryImplementationBuilder = new UnionFactoryImplementationBuilder(className, typeVariableInfos
                , factoryInterfaceBuilder, unionClassBuilders);
        writeFileSpec(factoryImplementationBuilder);
    }

    private void writeFileSpec(final GenericsTypeSpecBuilder genericsTypeSpecBuilder) throws IOException {
        FileSpecWriter.writeFileSpec(outputDir
                , genericsTypeSpecBuilder.getClassName().packageName()
                , genericsTypeSpecBuilder.getTypeSpecBuilder().build());
    }

}
