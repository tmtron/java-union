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

import com.tmtron.java.union.lib.Union3;
import com.tmtron.java.union.lib.j8.Consumer;
import com.tmtron.java.union.lib.j8.ObjectsJ8;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Union Implementation that has 3 different types.
 * @param <T1> type of element for this implementation class
 * @param <T2> type of element
 * @param <T3> type of element
 */
class Union3Imp1<T1, T2, T3> implements Union3<T1, T2, T3> {
  @Nonnull
  private final T1 value;

  Union3Imp1(@Nonnull final T1 value) {
    this.value = ObjectsJ8.requireNonNull(value);
  }

  /**
   * Executes the corresponding continuation depending on the value of the union
   * @param continuation1 will be executed when it is not null and the value is of type 1
   * @param continuation2 will be executed when it is not null and the value is of type 2
   * @param continuation3 will be executed when it is not null and the value is of type 3
   */
  @Override
  public final void execute(@Nullable Consumer<T1> continuation1,
      @Nullable Consumer<T2> continuation2, @Nullable Consumer<T3> continuation3) {
    try {
      if (continuation1 != null) {
        continuation1.accept(value);
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
    final Union3Imp1<?, ?, ?> that = (Union3Imp1<?, ?, ?>) o;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return "Union3Imp1{value="+value+"}";
  }
}
