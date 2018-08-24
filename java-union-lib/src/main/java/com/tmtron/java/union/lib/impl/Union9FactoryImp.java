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

import com.tmtron.java.union.lib.Union9;
import com.tmtron.java.union.lib.factories.Union9Factory;
import javax.annotation.CheckReturnValue;
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
class Union9FactoryImp<T1, T2, T3, T4, T5, T6, T7, T8, T9> implements Union9Factory<T1, T2, T3, T4, T5, T6, T7, T8, T9> {
  /**
   * @param element1 the value of the union element 1
   * @return an instance of Union9 for the union element 1
   */
  @CheckReturnValue
  @Override
  public final Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element1(@Nonnull T1 element1) {
    return new Union9Imp1<>(element1);
  }

  /**
   * @param element2 the value of the union element 2
   * @return an instance of Union9 for the union element 2
   */
  @CheckReturnValue
  @Override
  public final Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element2(@Nonnull T2 element2) {
    return new Union9Imp2<>(element2);
  }

  /**
   * @param element3 the value of the union element 3
   * @return an instance of Union9 for the union element 3
   */
  @CheckReturnValue
  @Override
  public final Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element3(@Nonnull T3 element3) {
    return new Union9Imp3<>(element3);
  }

  /**
   * @param element4 the value of the union element 4
   * @return an instance of Union9 for the union element 4
   */
  @CheckReturnValue
  @Override
  public final Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element4(@Nonnull T4 element4) {
    return new Union9Imp4<>(element4);
  }

  /**
   * @param element5 the value of the union element 5
   * @return an instance of Union9 for the union element 5
   */
  @CheckReturnValue
  @Override
  public final Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element5(@Nonnull T5 element5) {
    return new Union9Imp5<>(element5);
  }

  /**
   * @param element6 the value of the union element 6
   * @return an instance of Union9 for the union element 6
   */
  @CheckReturnValue
  @Override
  public final Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element6(@Nonnull T6 element6) {
    return new Union9Imp6<>(element6);
  }

  /**
   * @param element7 the value of the union element 7
   * @return an instance of Union9 for the union element 7
   */
  @CheckReturnValue
  @Override
  public final Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element7(@Nonnull T7 element7) {
    return new Union9Imp7<>(element7);
  }

  /**
   * @param element8 the value of the union element 8
   * @return an instance of Union9 for the union element 8
   */
  @CheckReturnValue
  @Override
  public final Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element8(@Nonnull T8 element8) {
    return new Union9Imp8<>(element8);
  }

  /**
   * @param element9 the value of the union element 9
   * @return an instance of Union9 for the union element 9
   */
  @CheckReturnValue
  @Override
  public final Union9<T1, T2, T3, T4, T5, T6, T7, T8, T9> element9(@Nonnull T9 element9) {
    return new Union9Imp9<>(element9);
  }
}
