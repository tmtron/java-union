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
package com.tmtron.java.union.internal.gen.functions;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeSpec;
import com.tmtron.java.union.internal.gen.shared.FileSpecWriter;
import com.tmtron.java.union.internal.gen.shared.Nullability;
import com.tmtron.java.union.internal.gen.shared.Util;
import com.tmtron.javapoet.TypeVariableInfos;

import java.io.IOException;
import java.nio.file.Path;

public class FunctionsGenerator {

    private final Path outputDir;

    public FunctionsGenerator(final Path outputDir) {
        this.outputDir = outputDir;
    }

    public void work() throws IOException {
        for (Nullability nullability : Nullability.values()) {
            for (int fileIndex = 0; fileIndex <= 9; fileIndex++) {
                TypeVariableInfos typeVariableInfos = TypeVariableInfos.create(fileIndex);
                final String interfaceName = "Function" + fileIndex + nullability.getNullableIdentifierNameOrBlank();
                final ClassName className = ClassName.get(Util.PACKAGE_NAME_FUNCTIONS, interfaceName);
                FunctionInterfaceBuilder functionInterfaceBuilder = new FunctionInterfaceBuilder(className,
                        typeVariableInfos, nullability);
                TypeSpec typeSpec = functionInterfaceBuilder.getTypeSpecBuilder().build();
                FileSpecWriter.writeFileSpec(outputDir, className.packageName(), typeSpec);
            }
        }
    }

}
