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

import com.tmtron.java.union.lib.Union7Nullable;
import javax.annotation.Nullable;

/**
 * Factory to create instances of Union7
 * @param <T1> the value of the union element 1
 * @param <T2> the value of the union element 2
 * @param <T3> the value of the union element 3
 * @param <T4> the value of the union element 4
 * @param <T5> the value of the union element 5
 * @param <T6> the value of the union element 6
 * @param <T7> the value of the union element 7
 */
public interface Union7FactoryNullable<T1, T2, T3, T4, T5, T6, T7> {
  /**
   * @param element1 the value of the union element 1
   * @return an instance of Union7 for the union element 1
   */
  Union7Nullable<T1, T2, T3, T4, T5, T6, T7> element1(@Nullable T1 element1);

  /**
   * @param element2 the value of the union element 2
   * @return an instance of Union7 for the union element 2
   */
  Union7Nullable<T1, T2, T3, T4, T5, T6, T7> element2(@Nullable T2 element2);

  /**
   * @param element3 the value of the union element 3
   * @return an instance of Union7 for the union element 3
   */
  Union7Nullable<T1, T2, T3, T4, T5, T6, T7> element3(@Nullable T3 element3);

  /**
   * @param element4 the value of the union element 4
   * @return an instance of Union7 for the union element 4
   */
  Union7Nullable<T1, T2, T3, T4, T5, T6, T7> element4(@Nullable T4 element4);

  /**
   * @param element5 the value of the union element 5
   * @return an instance of Union7 for the union element 5
   */
  Union7Nullable<T1, T2, T3, T4, T5, T6, T7> element5(@Nullable T5 element5);

  /**
   * @param element6 the value of the union element 6
   * @return an instance of Union7 for the union element 6
   */
  Union7Nullable<T1, T2, T3, T4, T5, T6, T7> element6(@Nullable T6 element6);

  /**
   * @param element7 the value of the union element 7
   * @return an instance of Union7 for the union element 7
   */
  Union7Nullable<T1, T2, T3, T4, T5, T6, T7> element7(@Nullable T7 element7);
}
