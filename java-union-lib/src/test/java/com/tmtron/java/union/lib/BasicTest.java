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
package com.tmtron.java.union.lib;

import com.tmtron.java.union.lib.functions.Function1;


import org.junit.Test;

public class BasicTest {

    private String func1(Boolean input) {
        if (input) return "Yes";
        return "No";
    }

    private String func1ex(Boolean input) throws Exception {
        if (input) throw new Exception("test");
        return "No";
    }

    @Test
    public void testFunctionAssignment() {
        // we can use Function1 which declares a checked exception for a function that does not declare this exception
        Function1<Boolean, String> function1 = this::func1;
        Function1<Boolean, String> function1Ex = this::func1ex;
    }
}
