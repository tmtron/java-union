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
package com.tmtron.java.union.internal.gen.functions;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

public class GenFunctions {

    private static final String PACKAGE_NAME = "com.tmtron.java.union.lib.functions";
    private final Path outputDir;
    private int currentParam;
    private final boolean throwsException;
    private final List<TypeFragment> fragments = new ArrayList<>();
    private final Map<Integer, TypeSpec> functionTypeSpecs = new HashMap<>();

    public GenFunctions(final Path outputDir, final boolean throwsException) {
        this.outputDir = outputDir;
        this.throwsException = throwsException;
    }

    public void writeFunctionFiles() throws IOException {
        if (!functionTypeSpecs.isEmpty()) throw new RuntimeException("functionTypeSpecs already written!");

        for (int i = 0; i <= 9; i++) {
            currentParam = i;
            writeFunctionFile();
        }
    }

    public TypeSpec getFunctionTypeSpec(int noOfParameters) {
        if (functionTypeSpecs.isEmpty()) throw new RuntimeException("functionTypeSpecs not written yet!");
        return functionTypeSpecs.get(noOfParameters);
    }

    private void writeFunctionFile() throws IOException {
        JavaFile.builder(PACKAGE_NAME, getFunctionFileSpec())
                .skipJavaLangImports(true)
                .addFileComment(getFileComment())
                .build()
                .writeTo(outputDir);
    }

    private String getFileComment() throws IOException {
        final String copyrightYear = new SimpleDateFormat("yyyy").format(new Date());
        final String currentDir = System.getProperty("user.dir");
        Path rootDir = Paths.get(currentDir, "LICENSE_HEADER");
        List<String> licenseHeaderTemplate = Files.readAllLines(rootDir);
        return licenseHeaderTemplate.stream()
                .map(raw -> raw.replace("${year}", copyrightYear))
                .collect(Collectors.joining("\n"));
    }

    private TypeSpec getFunctionFileSpec() {
        final String classNameStr;
        if (throwsException) {
            classNameStr = "FunctionEx"+currentParam;
        } else {
            classNameStr = "Function"+currentParam;
        }

        ClassName className = ClassName.get(PACKAGE_NAME, classNameStr);
        TypeSpec.Builder typeSpecBuilder = TypeSpec.interfaceBuilder(className)
                .addModifiers(Modifier.PUBLIC);

        initFragments(typeSpecBuilder);
        processFragments();

        TypeSpec result = typeSpecBuilder.build();
        functionTypeSpecs.put(currentParam, result);
        return result;
    }

    private void processFragments() {
        fragments.forEach(TypeFragment::prepare);
        fragments.forEach(fragment -> {
                    for (int i = 1; i < currentParam+1; i++) {
                        TypeVariableName typeVariableName = TypeVariableName.get("T" + i);
                        fragment.work(i, typeVariableName);
                    }
        });
        fragments.forEach(TypeFragment::finish);
    }

    private void initFragments(final TypeSpec.Builder result) {
        fragments.clear();
        final TypeFragment.Config config = new TypeFragment.Config(result, currentParam, throwsException);

        fragments.add(new ClassTypeVariables(config));
        fragments.add(new JavaDoc(config));
        fragments.add(new ApplyMethod(config));
    }

}
