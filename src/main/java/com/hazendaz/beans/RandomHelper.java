/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * The Class RandomHelper.
 */
public final class RandomHelper {
    /**
     * Gen rand.
     *
     * @return the int
     */
    public static int genRand() {
        return RandomHelper.rand(1, 10000);
    }

    /**
     * Rand.
     *
     * @param lo
     *            the lo
     * @param hi
     *            the hi
     *
     * @return the int
     */
    public static int rand(final int lo, final int hi) {
        final Random rn2 = new Random();
        final int n = hi - lo + 1;
        int i = (rn2.nextBoolean() ? 1 : -1) * rn2.nextInt(n);

        if (i < 0) {
            i = -i;
        }

        return lo + i;
    }

    /**
     * Randomstring.
     *
     * @param lo
     *            the lo
     * @param hi
     *            the hi
     *
     * @return the string
     */
    public static String randomstring(final int lo, final int hi) {
        final int n = RandomHelper.rand(lo, hi);
        final byte[] b = new byte[n];

        for (int i = 0; i < n; i++) {
            b[i] = (byte) RandomHelper.rand('A', 'Z');
        }

        return new String(b, StandardCharsets.UTF_8);
    }

    /**
     * Instantiates a new random helper.
     */
    private RandomHelper() {

    }
}
