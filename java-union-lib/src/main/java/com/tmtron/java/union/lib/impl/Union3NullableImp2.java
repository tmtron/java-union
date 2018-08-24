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
import com.tmtron.java.union.lib.j8.ConsumerNullable;
import javax.annotation.Nullable;

/**
 * Union Implementation that has 3 different types.
 * @param <T1> type of element
 * @param <T2> type of element for this implementation class
 * @param <T3> type of element
 */
class Union3NullableImp2<T1, T2, T3> implements Union3Nullable<T1, T2, T3> {
  @Nullable
  private final T2 value;

  Union3NullableImp2(@Nullable final T2 value) {
    this.value = value;
  }

  /**
   * Executes the corresponding continuation depending on the value of the union
   * @param continuation1 will be executed when it is not null and the value is of type 1
   * @param continuation2 will be executed when it is not null and the value is of type 2
   * @param continuation3 will be executed when it is not null and the value is of type 3
   */
  @Override
  public final void execute(@Nullable ConsumerNullable<T1> continuation1,
      @Nullable ConsumerNullable<T2> continuation2, @Nullable ConsumerNullable<T3> continuation3) {
    try {
      if (continuation2 != null) {
        continuation2.accept(value);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass() ) {
      return false;
    }
    final Union3NullableImp2<?, ?, ?> that = (Union3NullableImp2<?, ?, ?>) o;
    return value != null ? value.equals(that.value) : that.value == null;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Union3NullableImp2{value="+value+"}";
  }
}
