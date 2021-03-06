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

import com.tmtron.java.union.lib.Union2;
import com.tmtron.java.union.lib.factories.Union2Factory;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * Factory to create instances of Union2
 * @param <T1> the value of the union element 1
 * @param <T2> the value of the union element 2
 */
class Union2FactoryImp<T1, T2> implements Union2Factory<T1, T2> {
  /**
   * @param element1 the value of the union element 1
   * @return an instance of Union2 for the union element 1
   */
  @CheckReturnValue
  @Override
  public final Union2<T1, T2> element1(@Nonnull T1 element1) {
    return new Union2Imp1<>(element1);
  }

  /**
   * @param element2 the value of the union element 2
   * @return an instance of Union2 for the union element 2
   */
  @CheckReturnValue
  @Override
  public final Union2<T1, T2> element2(@Nonnull T2 element2) {
    return new Union2Imp2<>(element2);
  }
}
