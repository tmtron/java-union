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

public abstract class FileWriter {

    protected final String packageName;
    private final Path outputDir;
    protected final boolean throwsException;
    private boolean filesWritten;

    public FileWriter(final Path outputDir, final boolean throwsException, final String packageName) {
        this.outputDir = outputDir;
        this.throwsException = throwsException;
        this.packageName = packageName;
        this.filesWritten = false;
    }

    public void writeFiles() throws IOException {
        if (filesWritten) throw new RuntimeException("files have already been written!");
        for (int i = 0; i <= 9; i++) {
            writeFile(i);
        }
        filesWritten = true;
    }

    private void writeFile(int currentParam) throws IOException {
        JavaFile.builder(packageName, getFunctionFileSpec(currentParam, throwsException))
                .skipJavaLangImports(true)
                .addFileComment(getFileComment())
                .build()
                .writeTo(outputDir);
    }

    private String getFileComment() throws IOException {
        final String copyrightYear = new SimpleDateFormat("yyyy").format(new Date());
        final String currentDir = System.getProperty("user.dir");
        Path rootDir = Paths.get(currentDir, "LICENSE_HEADER");
        List<String> licenseHeaderTemplate = Files.readAllLines(rootDir);
        return licenseHeaderTemplate.stream()
                .map(raw -> raw.replace("${year}", copyrightYear))
                .collect(Collectors.joining("\n"));
    }

    protected abstract TypeSpec getFunctionFileSpec(int currentParam, boolean throwsException);
}
