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
package com.tmtron.java.union.internal.gen.shared;

import com.squareup.javapoet.ClassName;
import com.tmtron.java.union.lib.j8.Consumer;
import com.tmtron.java.union.lib.j8.ConsumerNullable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum Nullability {

    NULLABLE("Nullable", ConsumerNullable.class, Nullable.class),
    NONNULL("", Consumer.class, Nonnull.class);

    private final String nullableIdentifierNameOrBlank;
    private final Class<?> consumerClass;
    private final Class<?> nullAnnotationClass;

    Nullability(final String nullableIdentifierNameOrBlank, final Class<?> consumerClass,
                final Class<?> nullAnnotationClass) {
        this.nullableIdentifierNameOrBlank = nullableIdentifierNameOrBlank;
        this.consumerClass = consumerClass;
        this.nullAnnotationClass = nullAnnotationClass;
    }

    /**
     * Can be used as part of classnames, etc.
     * <ul>
     * <li>NONNULL returns blank, since we consider this the default. Thus a classname maybe "Union"</li>
     * <li>NULLABLE returns "Nullable". Thus a classname maybe "UnionNullable"</li>
     * </ul>
     *
     * @return the string "Nullable" or blank
     */
    public String getNullableIdentifierNameOrBlank() {
        return nullableIdentifierNameOrBlank;
    }

    /**
     * @return the Consumer class to use :e.g #Consumer
     */
    public Class<?> getConsumerClass() {
        return consumerClass;
    }

    /**
     * @return the ClassName of the Consumer class to use
     */
    public ClassName getConsumerClassName() {
        return ClassName.get(getConsumerClass());
    }

    public Class<?> getNullAnnotationClass() {
        return nullAnnotationClass;
    }

    public ClassName getNullAnnotationClassName() {
        return ClassName.get(getNullAnnotationClass());
    }
}
