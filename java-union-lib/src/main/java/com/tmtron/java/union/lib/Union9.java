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
 * This Union can have 9 different types.
 * @param <T1> type of element 1
 * @param <T2> type of element 2
 * @param <T3> type of element 3
 * @param <T4> type of element 4
 * @param <T5> type of element 5
 * @param <T6> type of element 6
 * @param <T7> type of element 7
 * @param <T8> type of element 8
 * @param <T9> type of element 9
 */
public interface Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> {
  /**
   * Executes the corresponding continuation depending on the value of the union
   * @param continuation1 will be executed when it is not null and the value is of type 1
   * @param continuation2 will be executed when it is not null and the value is of type 2
   * @param continuation3 will be executed when it is not null and the value is of type 3
   * @param continuation4 will be executed when it is not null and the value is of type 4
   * @param continuation5 will be executed when it is not null and the value is of type 5
   * @param continuation6 will be executed when it is not null and the value is of type 6
   * @param continuation7 will be executed when it is not null and the value is of type 7
   * @param continuation8 will be executed when it is not null and the value is of type 8
   * @param continuation9 will be executed when it is not null and the value is of type 9
   */
  void execute(@Nullable Consumer<T1> continuation1, @Nullable Consumer<T2> continuation2,
               @Nullable Consumer<T3> continuation3, @Nullable Consumer<T4> continuation4,
               @Nullable Consumer<T5> continuation5, @Nullable Consumer<T6> continuation6,
               @Nullable Consumer<T7> continuation7, @Nullable Consumer<T8> continuation8,
               @Nullable Consumer<T9> continuation9);
}