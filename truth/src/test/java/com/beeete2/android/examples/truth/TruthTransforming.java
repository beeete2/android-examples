package com.beeete2.android.examples.truth;

import static com.google.common.base.Functions.identity;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.truth.Correspondence;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/*
 * Copyright (c) 2016 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Taken from https://github.com/google/truth/blob/c60c71a2235a12be9808992de4b10ee2bf174217/core/src/main/java/com/google/common/truth/Correspondence.java
 */
public class TruthTransforming {

    public static <A, E> Correspondence<A, E> transforming(
            Function<A, ? extends E> actualTransform, String description) {
        return new Transforming<>(actualTransform, identity(), description);
    }

    private static final class Transforming<A, E> extends Correspondence<A, E> {

        private final Function<? super A, ?> actualTransform;
        private final Function<? super E, ?> expectedTransform;
        private final String description;

        private Transforming(
                Function<? super A, ?> actualTransform,
                Function<? super E, ?> expectedTransform,
                String description) {
            this.actualTransform = actualTransform;
            this.expectedTransform = expectedTransform;
            this.description = description;
        }

        @Override
        public boolean compare(@NullableDecl A actual, @NullableDecl E expected) {
            return Objects.equal(actualTransform.apply(actual), expectedTransform.apply(expected));
        }

        @Override
        public String toString() {
            return description;
        }
    }
}
