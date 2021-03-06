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

import com.squareup.javapoet.TypeVariableName;

public class Util {

    public static final String PACKAGE_NAME_ROOT = "com.tmtron.java.union.lib";
    public static final String PACKAGE_NAME_FACTORIES = PACKAGE_NAME_ROOT + ".factories";
    public static final String PACKAGE_NAME_IMPLEMENTATION = PACKAGE_NAME_ROOT + ".impl";
    public static final String PACKAGE_NAME_FUNCTIONS = PACKAGE_NAME_ROOT + ".functions";
    public static final TypeVariableName RESULT_TYPE_VARIABLE = TypeVariableName.get("R");

    public static final int MIN_INDEX_FOR_UNIONS = 2;
    public static final int MAX_INDEX_FOR_UNIONS = 9;
}
