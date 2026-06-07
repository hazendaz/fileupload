/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The Class RandomHelperTest.
 */
class RandomHelperTest {

    /**
     * Gen rand should stay within expected range.
     */
    @Test
    void genRandShouldStayWithinExpectedRange() {
        for (int i = 0; i < 100; i++) {
            final int value = RandomHelper.genRand();
            Assertions.assertTrue(value >= 1);
            Assertions.assertTrue(value <= 10000);
        }
    }

    /**
     * Rand should stay within provided bounds.
     */
    @Test
    void randShouldStayWithinProvidedBounds() {
        for (int i = 0; i < 100; i++) {
            final int value = RandomHelper.rand(10, 20);
            Assertions.assertTrue(value >= 10);
            Assertions.assertTrue(value <= 20);
        }
    }

    /**
     * Random string should contain only uppercase chars and expected length.
     */
    @Test
    void randomStringShouldContainOnlyUppercaseCharsAndExpectedLength() {
        final String generated = RandomHelper.randomstring(5, 5);

        Assertions.assertEquals(5, generated.length());
        for (final char character : generated.toCharArray()) {
            Assertions.assertTrue(character >= 'A' && character <= 'Z');
        }
    }
}
