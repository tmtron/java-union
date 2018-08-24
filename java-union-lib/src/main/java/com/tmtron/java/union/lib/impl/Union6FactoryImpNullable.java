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
package com.tmtron.java.union.lib.impl;

import com.tmtron.java.union.lib.Union6Nullable;
import com.tmtron.java.union.lib.factories.Union6FactoryNullable;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/**
 * Factory to create instances of Union6
 * @param <T1> the value of the union element 1
 * @param <T2> the value of the union element 2
 * @param <T3> the value of the union element 3
 * @param <T4> the value of the union element 4
 * @param <T5> the value of the union element 5
 * @param <T6> the value of the union element 6
 */
class Union6FactoryImpNullable<T1, T2, T3, T4, T5, T6> implements Union6FactoryNullable<T1, T2, T3, T4, T5, T6> {
  /**
   * @param element1 the value of the union element 1
   * @return an instance of Union6 for the union element 1
   */
  @CheckReturnValue
  @Override
  public final Union6Nullable<T1, T2, T3, T4, T5, T6> element1(@Nullable T1 element1) {
    return new Union6NullableImp1<>(element1);
  }

  /**
   * @param element2 the value of the union element 2
   * @return an instance of Union6 for the union element 2
   */
  @CheckReturnValue
  @Override
  public final Union6Nullable<T1, T2, T3, T4, T5, T6> element2(@Nullable T2 element2) {
    return new Union6NullableImp2<>(element2);
  }

  /**
   * @param element3 the value of the union element 3
   * @return an instance of Union6 for the union element 3
   */
  @CheckReturnValue
  @Override
  public final Union6Nullable<T1, T2, T3, T4, T5, T6> element3(@Nullable T3 element3) {
    return new Union6NullableImp3<>(element3);
  }

  /**
   * @param element4 the value of the union element 4
   * @return an instance of Union6 for the union element 4
   */
  @CheckReturnValue
  @Override
  public final Union6Nullable<T1, T2, T3, T4, T5, T6> element4(@Nullable T4 element4) {
    return new Union6NullableImp4<>(element4);
  }

  /**
   * @param element5 the value of the union element 5
   * @return an instance of Union6 for the union element 5
   */
  @CheckReturnValue
  @Override
  public final Union6Nullable<T1, T2, T3, T4, T5, T6> element5(@Nullable T5 element5) {
    return new Union6NullableImp5<>(element5);
  }

  /**
   * @param element6 the value of the union element 6
   * @return an instance of Union6 for the union element 6
   */
  @CheckReturnValue
  @Override
  public final Union6Nullable<T1, T2, T3, T4, T5, T6> element6(@Nullable T6 element6) {
    return new Union6NullableImp6<>(element6);
  }
}
