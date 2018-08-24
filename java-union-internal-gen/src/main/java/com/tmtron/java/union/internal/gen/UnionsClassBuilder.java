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
import com.squareup.javapoet.TypeSpec;
import com.tmtron.java.union.internal.gen.shared.FileSpecWriter;
import com.tmtron.javapoet.TypeSpecBuilder;

import java.io.IOException;
import java.nio.file.Path;

import javax.lang.model.element.Modifier;

/**
 * creates the Unions class which is the entry point for the user to create union-factories
 */
class UnionsClassBuilder extends TypeSpecBuilder {

    private final Path outputDir;

    UnionsClassBuilder(Path outputDir, final ClassName unionsClassName) {
        super(unionsClassName, TypeSpec.classBuilder(unionsClassName));
        this.outputDir = outputDir;

        typeSpecBuilder.addModifiers(Modifier.PUBLIC);
        typeSpecBuilder.addJavadoc("This is the entry point class to create unions.");
    }

    void writeFile() throws IOException {
        FileSpecWriter.writeFileSpec(outputDir, getClassName().packageName(), getTypeSpecBuilder().build());
    }
}
