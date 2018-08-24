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

import com.tmtron.java.union.lib.factories.Union2Factory;
import com.tmtron.java.union.lib.factories.Union2FactoryNullable;
import com.tmtron.java.union.lib.factories.Union3Factory;
import com.tmtron.java.union.lib.factories.Union3FactoryNullable;
import com.tmtron.java.union.lib.factories.Union4Factory;
import com.tmtron.java.union.lib.factories.Union4FactoryNullable;
import com.tmtron.java.union.lib.factories.Union5Factory;
import com.tmtron.java.union.lib.factories.Union5FactoryNullable;
import com.tmtron.java.union.lib.factories.Union6Factory;
import com.tmtron.java.union.lib.factories.Union6FactoryNullable;
import com.tmtron.java.union.lib.factories.Union7Factory;
import com.tmtron.java.union.lib.factories.Union7FactoryNullable;
import com.tmtron.java.union.lib.factories.Union8Factory;
import com.tmtron.java.union.lib.factories.Union8FactoryNullable;
import com.tmtron.java.union.lib.factories.Union9Factory;
import com.tmtron.java.union.lib.factories.Union9FactoryNullable;

/**
 * This is the entry point class to create unions. */
public class Unions {
  /**
   * Creates a Nullable union 2 factory */
  public static <T1, T2> Union2FactoryNullable<T1, T2> factory2Nullable() {
    return new Union2FactoryImpNullable<>();
  }

  /**
   * Creates a Nullable union 3 factory */
  public static <T1, T2, T3> Union3FactoryNullable<T1, T2, T3> factory3Nullable() {
    return new Union3FactoryImpNullable<>();
  }

  /**
   * Creates a Nullable union 4 factory */
  public static <T1, T2, T3, T4> Union4FactoryNullable<T1, T2, T3, T4> factory4Nullable() {
    return new Union4FactoryImpNullable<>();
  }

  /**
   * Creates a Nullable union 5 factory */
  public static <T1, T2, T3, T4, T5> Union5FactoryNullable<T1, T2, T3, T4, T5> factory5Nullable() {
    return new Union5FactoryImpNullable<>();
  }

  /**
   * Creates a Nullable union 6 factory */
  public static <T1, T2, T3, T4, T5, T6> Union6FactoryNullable<T1, T2, T3, T4, T5, T6> factory6Nullable(
      ) {
    return new Union6FactoryImpNullable<>();
  }

  /**
   * Creates a Nullable union 7 factory */
  public static <T1, T2, T3, T4, T5, T6, T7> Union7FactoryNullable<T1, T2, T3, T4, T5, T6, T7> factory7Nullable(
      ) {
    return new Union7FactoryImpNullable<>();
  }

  /**
   * Creates a Nullable union 8 factory */
  public static <T1, T2, T3, T4, T5, T6, T7, T8> Union8FactoryNullable<T1, T2, T3, T4, T5, T6, T7, T8> factory8Nullable(
      ) {
    return new Union8FactoryImpNullable<>();
  }

  /**
   * Creates a Nullable union 9 factory */
  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Union9FactoryNullable<T1, T2, T3, T4, T5, T6, T7, T8, T9> factory9Nullable(
      ) {
    return new Union9FactoryImpNullable<>();
  }

  /**
   * Creates a union 2 factory */
  public static <T1, T2> Union2Factory<T1, T2> factory2() {
    return new Union2FactoryImp<>();
  }

  /**
   * Creates a union 3 factory */
  public static <T1, T2, T3> Union3Factory<T1, T2, T3> factory3() {
    return new Union3FactoryImp<>();
  }

  /**
   * Creates a union 4 factory */
  public static <T1, T2, T3, T4> Union4Factory<T1, T2, T3, T4> factory4() {
    return new Union4FactoryImp<>();
  }

  /**
   * Creates a union 5 factory */
  public static <T1, T2, T3, T4, T5> Union5Factory<T1, T2, T3, T4, T5> factory5() {
    return new Union5FactoryImp<>();
  }

  /**
   * Creates a union 6 factory */
  public static <T1, T2, T3, T4, T5, T6> Union6Factory<T1, T2, T3, T4, T5, T6> factory6() {
    return new Union6FactoryImp<>();
  }

  /**
   * Creates a union 7 factory */
  public static <T1, T2, T3, T4, T5, T6, T7> Union7Factory<T1, T2, T3, T4, T5, T6, T7> factory7() {
    return new Union7FactoryImp<>();
  }

  /**
   * Creates a union 8 factory */
  public static <T1, T2, T3, T4, T5, T6, T7, T8> Union8Factory<T1, T2, T3, T4, T5, T6, T7, T8> factory8(
      ) {
    return new Union8FactoryImp<>();
  }

  /**
   * Creates a union 9 factory */
  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Union9Factory<T1, T2, T3, T4, T5, T6, T7, T8, T9> factory9(
      ) {
    return new Union9FactoryImp<>();
  }
}
