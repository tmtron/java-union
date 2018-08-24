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
package com.tmtron.java.union.internal.gen.shared;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FileSpecWriter {

    public static void writeFileSpec(final Path outputDir, final String packageName, final TypeSpec typeSpec) throws IOException {
        JavaFile.Builder builder = JavaFile.builder(packageName, typeSpec);
        builder.skipJavaLangImports(true)
                .addFileComment(getFileHeader())
                .build()
                .writeTo(outputDir);
    }

    private static String getFileHeader() throws IOException {
        final String copyrightYear = new SimpleDateFormat("yyyy").format(new Date());
        final String currentDir = System.getProperty("user.dir");
        Path rootDir = Paths.get(currentDir, "LICENSE_HEADER");
        List<String> licenseHeaderTemplate = Files.readAllLines(rootDir);
        // add empty line before and after the header, so that the check of the license gradle plugin works
        licenseHeaderTemplate.add(0, "");
        licenseHeaderTemplate.add("");
        return licenseHeaderTemplate.stream()
                .map(raw -> raw.replace("${year}", copyrightYear))
                .collect(Collectors.joining("\n"));
    }
}
