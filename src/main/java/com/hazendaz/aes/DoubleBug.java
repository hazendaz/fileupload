/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
 */
package com.hazendaz.aes;

public class DoubleBug {
    public static void main(final String[] args) {
        System.out.println("Test:");
        final double d = Double.parseDouble("2.2250738585072012e-308");
        System.out.println("Value: " + d);
    }
}
