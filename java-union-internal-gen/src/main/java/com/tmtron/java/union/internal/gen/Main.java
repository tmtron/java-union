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
package com.tmtron.java.union.internal.gen;

import com.tmtron.java.union.internal.gen.functions.GenFunctions;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {
        System.out.println("starting..");

        final String currentDir = System.getProperty("user.dir");
        Path outputDir = Paths.get(currentDir, "build", "generated");

        try {
            for (int i = 0; i <= 1; i++) {
                boolean throwsException = i == 0;
                GenFunctions genFunctions = new GenFunctions(outputDir, throwsException);
                genFunctions.writeFiles();




            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("FAILED TO WRITE FILE");
            System.exit(-1);
        }
        System.out.println("done!");
    }

}
