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
package com.tmtron.java.union.internal.gen.unionsimpl;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeSpec;
import com.tmtron.java.union.internal.gen.shared.FileWriter;
import com.tmtron.java.union.internal.gen.shared.TypeFragment;
import com.tmtron.java.union.internal.gen.shared.Util;
import com.tmtron.java.union.internal.gen.unions.GenUnions;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

public class GenUnionsImpl extends FileWriter {

    private final List<TypeFragment4Impl> fragments = new ArrayList<>();
    private final GenUnions genUnions;
    private int implementationIndex;
    private final Map<String, ClassName> generatedClassNames = new HashMap<>();

    public GenUnionsImpl(final Path outputDir, final boolean isNullable, final GenUnions genUnions) {
        super(outputDir, isNullable, Util.PACKAGE_NAME_UNIONS_IMPLEMENTATION, Util.MIN_INDEX_FOR_UNIONS);
        this.genUnions = genUnions;
    }

    @Override
    protected void writeFile(final int unionArity) throws IOException {
        for (int i = 1; i <= unionArity; i++) {
            implementationIndex = i;
            super.writeFile(unionArity);
        }
    }

    private String getMapKey(int unionIndex, int implementationIndex) {
        return unionIndex + "-" + implementationIndex;
    }

    public ClassName getGeneratedClassName(int unionIndex, int implementationIndex) {
        final String mapKey = getMapKey(unionIndex, implementationIndex);
        return generatedClassNames.get(mapKey);
    }

    @Override
    protected TypeSpec getFunctionFileSpec(int unionIndex, boolean isNullable) {
        final String classNameStr =
                "Union" + unionIndex + Util.getNullableIdentifierNameOrBlank(isNullable) + "Imp" + implementationIndex;

        ClassName className = ClassName.get(packageName, classNameStr);
        final String mapKey = getMapKey(unionIndex, implementationIndex);
        generatedClassNames.put(mapKey, className);
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.FINAL);

        initFragments(className, typeSpecBuilder, unionIndex);
        processFragments(unionIndex);

        return typeSpecBuilder.build();
    }

    private void processFragments(int currentParam) {
        fragments.forEach(TypeFragment4Impl::prepare);
        fragments.forEach(fragment -> {
            for (int i = 1; i < currentParam + 1; i++) {
                fragment.work(new ImplWorkParams(implementationIndex, i));
            }
        });
        fragments.forEach(TypeFragment4Impl::finish);
    }

    private void initFragments(final ClassName className, final TypeSpec.Builder result, int currentParam) {
        fragments.clear();
        final TypeFragment.Config config = new TypeFragment.Config(result, currentParam, isNullable);
        final TypeFragment4Impl.ImplConfig implConfig = new TypeFragment4Impl.ImplConfig(className, genUnions, config);

        fragments.add(new JavaDoc(implConfig));
        fragments.add(new ClassTypeVariables(implConfig));
        fragments.add(new Methods(implConfig));
    }

}
