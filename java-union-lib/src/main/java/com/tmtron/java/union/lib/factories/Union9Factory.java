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
package com.tmtron.java.union.lib.factories;

import com.tmtron.java.union.lib.Union9;
import javax.annotation.Nonnull;

/**
 * Factory to create instances of Union9
 * @param <T1> the value of the union element 1
 * @param <T2> the value of the union element 2
 * @param <T3> the value of the union element 3
 * @param <T4> the value of the union element 4
 * @param <T5> the value of the union element 5
 * @param <T6> the value of the union element 6
 * @param <T7> the value of the union element 7
 * @param <T8> the value of the union element 8
 * @param <T9> the value of the union element 9
 */
public interface Union9Factory<T1, T2, T3, T4, T5, T6, T7, T8, T9> {
  /**
   * @param element1 the value of the union element 1
   * @return an instance of Union9 for the union element 1
   */
  Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element1(@Nonnull T1 element1);

  /**
   * @param element2 the value of the union element 2
   * @return an instance of Union9 for the union element 2
   */
  Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element2(@Nonnull T2 element2);

  /**
   * @param element3 the value of the union element 3
   * @return an instance of Union9 for the union element 3
   */
  Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element3(@Nonnull T3 element3);

  /**
   * @param element4 the value of the union element 4
   * @return an instance of Union9 for the union element 4
   */
  Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element4(@Nonnull T4 element4);

  /**
   * @param element5 the value of the union element 5
   * @return an instance of Union9 for the union element 5
   */
  Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element5(@Nonnull T5 element5);

  /**
   * @param element6 the value of the union element 6
   * @return an instance of Union9 for the union element 6
   */
  Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element6(@Nonnull T6 element6);

  /**
   * @param element7 the value of the union element 7
   * @return an instance of Union9 for the union element 7
   */
  Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element7(@Nonnull T7 element7);

  /**
   * @param element8 the value of the union element 8
   * @return an instance of Union9 for the union element 8
   */
  Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element8(@Nonnull T8 element8);

  /**
   * @param element9 the value of the union element 9
   * @return an instance of Union9 for the union element 9
   */
  Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element9(@Nonnull T9 element9);
}
