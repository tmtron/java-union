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
import com.tmtron.java.union.lib.j8.ConsumerNullable;
import javax.annotation.Nullable;

/**
 * Union Implementation that has 6 different types.
 * @param <T1> type of element
 * @param <T2> type of element
 * @param <T3> type of element
 * @param <T4> type of element
 * @param <T5> type of element
 * @param <T6> type of element for this implementation class
 */
class Union6NullableImp6<T1, T2, T3, T4, T5, T6> implements Union6Nullable<T1, T2, T3, T4, T5, T6> {
  @Nullable
  private final T6 value;

  Union6NullableImp6(@Nullable final T6 value) {
    this.value = value;
  }

  /**
   * Executes the corresponding continuation depending on the value of the union
   * @param continuation1 will be executed when it is not null and the value is of type 1
   * @param continuation2 will be executed when it is not null and the value is of type 2
   * @param continuation3 will be executed when it is not null and the value is of type 3
   * @param continuation4 will be executed when it is not null and the value is of type 4
   * @param continuation5 will be executed when it is not null and the value is of type 5
   * @param continuation6 will be executed when it is not null and the value is of type 6
   */
  @Override
  public final void execute(@Nullable ConsumerNullable<T1> continuation1,
      @Nullable ConsumerNullable<T2> continuation2, @Nullable ConsumerNullable<T3> continuation3,
      @Nullable ConsumerNullable<T4> continuation4, @Nullable ConsumerNullable<T5> continuation5,
      @Nullable ConsumerNullable<T6> continuation6) {
    try {
      if (continuation6 != null) {
        continuation6.accept(value);
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
    final Union6NullableImp6<?, ?, ?, ?, ?, ?> that = (Union6NullableImp6<?, ?, ?, ?, ?, ?>) o;
    return value != null ? value.equals(that.value) : that.value == null;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Union6NullableImp6{value="+value+"}";
  }
}
