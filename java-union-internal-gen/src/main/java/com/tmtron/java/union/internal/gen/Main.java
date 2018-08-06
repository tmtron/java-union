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

import com.tmtron.java.union.internal.gen.factories.GenFactories;
import com.tmtron.java.union.internal.gen.functions.GenFunctions;
import com.tmtron.java.union.internal.gen.unions.GenUnions;
import com.tmtron.java.union.internal.gen.unionsimpl.GenUnionsImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        System.out.println("starting..");

        final String currentDir = System.getProperty("user.dir");
        Path outputDir = Paths.get(currentDir, "build", "generated", "unions");

        try {
            generateFiles(outputDir, false);
            generateFiles(outputDir, true);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("FAILED TO WRITE FILE");
            System.exit(-1);
        }
        System.out.println("done!");
    }

    private static void generateFiles(final Path outputDir, final boolean isNullable) throws IOException {
        final GenFunctions genFunctions = new GenFunctions(outputDir, isNullable);
        genFunctions.writeFiles();

        final GenUnions genUnions = new GenUnions(outputDir, isNullable);
        genUnions.writeFiles();
        new GenFactories(outputDir, isNullable, genUnions).writeFiles();

        new GenUnionsImpl(outputDir, isNullable, genUnions).writeFiles();
    }

}
