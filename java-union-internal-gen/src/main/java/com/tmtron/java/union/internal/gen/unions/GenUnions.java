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
package com.tmtron.java.union.internal.gen.unions;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeSpec;
import com.tmtron.java.union.internal.gen.functions.GenFunctions;
import com.tmtron.java.union.internal.gen.shared.FileWriter;
import com.tmtron.java.union.internal.gen.shared.TypeFragment;
import com.tmtron.java.union.internal.gen.shared.Util;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

public class GenUnions extends FileWriter {

    private final List<TypeFragment> fragments = new ArrayList<>();
    private final GenFunctions genFunctions;
    private final Map<Integer, TypeSpec> generatedClasses = new HashMap<>();

    public GenUnions(final Path outputDir, final boolean throwsException, final GenFunctions genFunctions) {
        super(outputDir, throwsException, Util.ROOT_PACKAGE_NAME, Util.MIN_INDEX_FOR_UNIONS);
        this.genFunctions = genFunctions;
    }

    public TypeSpec getGeneratedClass(int noOfTypeVariables) {
        if (generatedClasses.isEmpty()) throw new RuntimeException("generatedClasses not written yet!");
        return generatedClasses.get(noOfTypeVariables);
    }

    public ClassName getGeneratedClassName(int noOfTypeVariables) {
        return ClassName.get(packageName, getGeneratedClass(noOfTypeVariables).name);
    }

    @Override
    protected TypeSpec getFunctionFileSpec(int noOfTypeVariables, boolean throwsException) {
        final String classNameStr = "Union" + noOfTypeVariables;

        ClassName className = ClassName.get(packageName, classNameStr);
        TypeSpec.Builder typeSpecBuilder = TypeSpec.interfaceBuilder(className)
                .addModifiers(Modifier.PUBLIC);

        initFragments(typeSpecBuilder, noOfTypeVariables);
        processFragments(noOfTypeVariables);

        TypeSpec result = typeSpecBuilder.build();
        generatedClasses.put(noOfTypeVariables, result);
        return result;
    }

    private void processFragments(int currentParam) {
        fragments.forEach(TypeFragment::prepare);
        fragments.forEach(fragment -> {
            for (int i = 1; i < currentParam + 1; i++) {
                fragment.work(i);
            }
        });
        fragments.forEach(TypeFragment::finish);
    }

    private void initFragments(final TypeSpec.Builder result, int currentParam) {
        fragments.clear();
        final TypeFragment.Config config = new TypeFragment.Config(result, currentParam, throwsException);

        fragments.add(new JavaDoc(config));
        fragments.add(new ClassTypeVariables(config));
        fragments.add(new Methods(config));
    }

}
