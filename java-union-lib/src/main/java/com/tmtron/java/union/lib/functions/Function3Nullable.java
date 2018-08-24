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
package com.tmtron.java.union.lib.functions;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/**
 * A functional interface (callback) that returns a value.
 * @param <T1> the value type of element 1
 * @param <T2> the value type of element 2
 * @param <T3> the value type of element 3
 * @param <R> the result type
 */
public interface Function3Nullable<T1, T2, T3, R> {
  /**
   * returns a value based on the input parameters
   * @param p1 parameter 1
   * @param p2 parameter 2
   * @param p3 parameter 3
   * @return the result value
   * @throws Exception on error
   */
  @CheckReturnValue
  @Nullable
  R apply(@Nullable T1 p1, @Nullable T2 p2, @Nullable T3 p3) throws Exception;
}
