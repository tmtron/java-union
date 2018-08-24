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

import com.tmtron.java.union.lib.Union3Nullable;
import com.tmtron.java.union.lib.factories.Union3FactoryNullable;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/**
 * Factory to create instances of Union3
 * @param <T1> the value of the union element 1
 * @param <T2> the value of the union element 2
 * @param <T3> the value of the union element 3
 */
class Union3FactoryImpNullable<T1, T2, T3> implements Union3FactoryNullable<T1, T2, T3> {
  /**
   * @param element1 the value of the union element 1
   * @return an instance of Union3 for the union element 1
   */
  @CheckReturnValue
  @Override
  public final Union3Nullable<T1, T2, T3> element1(@Nullable T1 element1) {
    return new Union3NullableImp1<>(element1);
  }

  /**
   * @param element2 the value of the union element 2
   * @return an instance of Union3 for the union element 2
   */
  @CheckReturnValue
  @Override
  public final Union3Nullable<T1, T2, T3> element2(@Nullable T2 element2) {
    return new Union3NullableImp2<>(element2);
  }

  /**
   * @param element3 the value of the union element 3
   * @return an instance of Union3 for the union element 3
   */
  @CheckReturnValue
  @Override
  public final Union3Nullable<T1, T2, T3> element3(@Nullable T3 element3) {
    return new Union3NullableImp3<>(element3);
  }
}
