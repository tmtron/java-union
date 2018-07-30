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
                genFunctions.writeFunctionFiles();




            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("FAILED TO WRITE FILE");
            System.exit(-1);
        }
        System.out.println("done!");
    }

}
