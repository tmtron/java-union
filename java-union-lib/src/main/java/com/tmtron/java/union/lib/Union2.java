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

import com.tmtron.java.union.lib.j8.Consumer;
import javax.annotation.Nullable;

/**
 * This Union can have 2 different types.
 * @param <T1> type of element 1
 * @param <T2> type of element 2
 */
public interface Union2<T1, T2> {
  /**
   * Executes the corresponding continuation depending on the value of the union
   * @param continuation1 will be executed when it is not null and the value is of type 1
   * @param continuation2 will be executed when it is not null and the value is of type 2
   */
  void execute(@Nullable Consumer<T1> continuation1, @Nullable Consumer<T2> continuation2);
}
