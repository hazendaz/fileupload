/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
 */
package com.hazendaz.beans;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public final class RandomHelper {
    public static int genRand() {
        return RandomHelper.rand(1, 10000);
    }

    public static int rand(final int lo, final int hi) {
        final Random rn2 = new Random();
        final int n = hi - lo + 1;
        int i = (rn2.nextBoolean() ? 1 : -1) * rn2.nextInt(n);

        if (i < 0) {
            i = -i;
        }

        return lo + i;
    }

    public static String randomstring(final int lo, final int hi) {
        final int n = RandomHelper.rand(lo, hi);
        final byte[] b = new byte[n];

        for (int i = 0; i < n; i++) {
            b[i] = (byte) RandomHelper.rand('A', 'Z');
        }

        return new String(b, StandardCharsets.UTF_8);
    }

    private RandomHelper() {

    }
}
