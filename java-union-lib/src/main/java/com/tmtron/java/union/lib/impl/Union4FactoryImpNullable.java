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

import com.tmtron.java.union.lib.Union4Nullable;
import com.tmtron.java.union.lib.factories.Union4FactoryNullable;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/**
 * Factory to create instances of Union4
 * @param <T1> the value of the union element 1
 * @param <T2> the value of the union element 2
 * @param <T3> the value of the union element 3
 * @param <T4> the value of the union element 4
 */
class Union4FactoryImpNullable<T1, T2, T3, T4> implements Union4FactoryNullable<T1, T2, T3, T4> {
  /**
   * @param element1 the value of the union element 1
   * @return an instance of Union4 for the union element 1
   */
  @CheckReturnValue
  @Override
  public final Union4Nullable<T1, T2, T3, T4> element1(@Nullable T1 element1) {
    return new Union4NullableImp1<>(element1);
  }

  /**
   * @param element2 the value of the union element 2
   * @return an instance of Union4 for the union element 2
   */
  @CheckReturnValue
  @Override
  public final Union4Nullable<T1, T2, T3, T4> element2(@Nullable T2 element2) {
    return new Union4NullableImp2<>(element2);
  }

  /**
   * @param element3 the value of the union element 3
   * @return an instance of Union4 for the union element 3
   */
  @CheckReturnValue
  @Override
  public final Union4Nullable<T1, T2, T3, T4> element3(@Nullable T3 element3) {
    return new Union4NullableImp3<>(element3);
  }

  /**
   * @param element4 the value of the union element 4
   * @return an instance of Union4 for the union element 4
   */
  @CheckReturnValue
  @Override
  public final Union4Nullable<T1, T2, T3, T4> element4(@Nullable T4 element4) {
    return new Union4NullableImp4<>(element4);
  }
}
