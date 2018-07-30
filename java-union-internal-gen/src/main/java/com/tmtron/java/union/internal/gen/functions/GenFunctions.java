package com.tmtron.java.union.internal.gen.functions;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

public class GenFunctions {

    private static final String PACKAGE_NAME = "com.tmtron.java.union.lib.functions";
    private final Path outputDir;
    private int currentParam;
    private final boolean throwsException;
    private final List<TypeFragment> fragments = new ArrayList<>();
    private final Map<Integer, TypeSpec> functionTypeSpecs = new HashMap<>();

    public GenFunctions(final Path outputDir, final boolean throwsException) {
        this.outputDir = outputDir;
        this.throwsException = throwsException;
    }

    public void writeFunctionFiles() throws IOException {
        if (!functionTypeSpecs.isEmpty()) throw new RuntimeException("functionTypeSpecs already written!");

        for (int i = 0; i <= 9; i++) {
            currentParam = i;
            writeFunctionFile();
        }
    }

    public TypeSpec getFunctionTypeSpec(int noOfParameters) {
        if (functionTypeSpecs.isEmpty()) throw new RuntimeException("functionTypeSpecs not written yet!");
        return functionTypeSpecs.get(noOfParameters);
    }

    private void writeFunctionFile() throws IOException {
        JavaFile.builder(PACKAGE_NAME, getFunctionFileSpec())
                .skipJavaLangImports(true)
                .build()
                .writeTo(outputDir);
    }

    private TypeSpec getFunctionFileSpec() {
        final String classNameStr;
        if (throwsException) {
            classNameStr = "FunctionEx"+currentParam;
        } else {
            classNameStr = "Function"+currentParam;
        }

        ClassName className = ClassName.get(PACKAGE_NAME, classNameStr);
        TypeSpec.Builder typeSpecBuilder = TypeSpec.interfaceBuilder(className)
                .addModifiers(Modifier.PUBLIC);

        initFragments(typeSpecBuilder);
        processFragments();

        TypeSpec result = typeSpecBuilder.build();
        functionTypeSpecs.put(currentParam, result);
        return result;
    }

    private void processFragments() {
        fragments.forEach(TypeFragment::prepare);
        fragments.forEach(fragment -> {
                    for (int i = 1; i < currentParam+1; i++) {
                        TypeVariableName typeVariableName = TypeVariableName.get("T" + i);
                        fragment.work(i, typeVariableName);
                    }
        });
        fragments.forEach(TypeFragment::finish);
    }

    private void initFragments(final TypeSpec.Builder result) {
        fragments.clear();
        final TypeFragment.Config config = new TypeFragment.Config(result, currentParam, throwsException);

        fragments.add(new ClassTypeVariables(config));
        fragments.add(new JavaDoc(config));
        fragments.add(new ApplyMethod(config));
    }

}
