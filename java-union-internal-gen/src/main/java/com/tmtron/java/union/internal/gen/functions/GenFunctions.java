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
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.tmtron.java.union.internal.gen.FileWriter;
import com.tmtron.java.union.internal.gen.TypeFragment;
import com.tmtron.java.union.internal.gen.Util;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

public class GenFunctions extends FileWriter {

    private final List<TypeFragment> fragments = new ArrayList<>();
    private final Map<Integer, TypeSpec> functionTypeSpecs = new HashMap<>();

    public GenFunctions(final Path outputDir, final boolean throwsException) {
        super(outputDir, throwsException, Util.ROOT_PACKAGE_NAME + "functions");
    }

    public TypeSpec getFunctionTypeSpec(int noOfParameters) {
        if (functionTypeSpecs.isEmpty()) throw new RuntimeException("functionTypeSpecs not written yet!");
        return functionTypeSpecs.get(noOfParameters);
    }

    @Override
    protected TypeSpec getFunctionFileSpec(int currentParam, boolean throwsException) {
        final String classNameStr;
        if (throwsException) {
            classNameStr = "FunctionEx"+currentParam;
        } else {
            classNameStr = "Function"+currentParam;
        }

        ClassName className = ClassName.get(packageName, classNameStr);
        TypeSpec.Builder typeSpecBuilder = TypeSpec.interfaceBuilder(className)
                .addModifiers(Modifier.PUBLIC);

        initFragments(typeSpecBuilder, currentParam);
        processFragments(currentParam);

        TypeSpec result = typeSpecBuilder.build();
        functionTypeSpecs.put(currentParam, result);
        return result;
    }

    private void processFragments(int currentParam) {
        fragments.forEach(TypeFragment::prepare);
        fragments.forEach(fragment -> {
                    for (int i = 1; i < currentParam+1; i++) {
                        TypeVariableName typeVariableName = TypeVariableName.get("T" + i);
                        fragment.work(i, typeVariableName);
                    }
        });
        fragments.forEach(TypeFragment::finish);
    }

    private void initFragments(final TypeSpec.Builder result, int currentParam) {
        fragments.clear();
        final TypeFragment.Config config = new TypeFragment.Config(result, currentParam, throwsException);

        fragments.add(new ClassTypeVariables(config));
        fragments.add(new JavaDoc(config));
        fragments.add(new ApplyMethod(config));
    }

}
