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
package com.tmtron.java.union.internal.gen.factories;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeSpec;
import com.tmtron.java.union.internal.gen.shared.FileWriter;
import com.tmtron.java.union.internal.gen.shared.TypeFragment;
import com.tmtron.java.union.internal.gen.shared.Util;
import com.tmtron.java.union.internal.gen.unions.GenUnions;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

public class GenFactories extends FileWriter {

    private final List<TypeFragment> fragments = new ArrayList<>();
    private final GenUnions genUnions;
    private final Map<Integer, ClassName> generatedFactoryClassNames = new HashMap<>();

    public GenFactories(final Path outputDir, final boolean isNullable, final GenUnions genUnions) {
        super(outputDir, isNullable, Util.PACKAGE_NAME_ROOT + ".factories", Util.MIN_INDEX_FOR_UNIONS);
        this.genUnions = genUnions;
    }

    @Override
    protected TypeSpec getFunctionFileSpec(int noOfTypeVariables, boolean isNullable) {
        final String classNameStr =
                "Union" + noOfTypeVariables + "Factory" + Util.getNullableIdentifierNameOrBlank(isNullable);

        ClassName className = ClassName.get(packageName, classNameStr);
        generatedFactoryClassNames.put(noOfTypeVariables, className);
        TypeSpec.Builder typeSpecBuilder = TypeSpec.interfaceBuilder(className)
                .addModifiers(Modifier.PUBLIC);

        initFragments(typeSpecBuilder, noOfTypeVariables);
        processFragments(noOfTypeVariables);

        return typeSpecBuilder.build();
    }

    public ClassName getFactoryClassName(int noOfTypeVariables) {
        return generatedFactoryClassNames.get(noOfTypeVariables);
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
        final TypeFragment.Config config = new TypeFragment.Config(result, currentParam, isNullable);

        fragments.add(new JavaDoc(config));
        fragments.add(new ClassTypeVariables(config));
        fragments.add(new Methods(config, genUnions));
    }

}
