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
import com.tmtron.java.union.internal.gen.shared.Nullability;
import com.tmtron.java.union.internal.gen.shared.Util;

import java.io.IOException;
import java.nio.file.Path;

/**
 * will execute a union-generator for union arity 2 to 9 and nullable/nonnull
 */
class UnionsGenerator {
    private final Path outputDir;
    private final UnionsClassBuilder unionsClassBuilder;

    UnionsGenerator(final Path outputDir) {
        this.outputDir = outputDir;
        final ClassName unionsClassName = ClassName.get(Util.PACKAGE_NAME_IMPLEMENTATION, "Unions");
        unionsClassBuilder = new UnionsClassBuilder(outputDir, unionsClassName);
    }

    void work() throws IOException {
        for (Nullability nullability : Nullability.values()) {
            for (int fileIndex = Util.MIN_INDEX_FOR_UNIONS; fileIndex <= Util.MAX_INDEX_FOR_UNIONS; fileIndex++) {
                UnionGenerator unionGenerator = new UnionGenerator(outputDir, nullability, fileIndex);
                unionGenerator.work(unionsClassBuilder);
            }
        }
        unionsClassBuilder.writeFile();
    }

}
