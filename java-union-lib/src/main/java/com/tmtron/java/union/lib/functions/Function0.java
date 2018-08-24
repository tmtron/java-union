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
import javax.annotation.Nonnull;

/**
 * A functional interface (callback) that returns a value.
 * @param <R> the result type
 */
public interface Function0<R> {
  /**
   * returns a value based on the input parameter
   * @return the result value
   * @throws Exception on error
   */
  @CheckReturnValue
  @Nonnull
  R apply() throws Exception;
}
