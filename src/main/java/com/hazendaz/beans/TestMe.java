/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

/**
 * The Class TestMe.
 */
public class TestMe {

    /**
     * Main.
     *
     * @param args
     *            the args
     */
    public static void main(final String[] args) {
        System.out.println(TestMe.toImpliedDecimal(null));
        System.out.println(TestMe.toImpliedDecimal(Double.valueOf(1)));
        System.out.println(TestMe.toImpliedDecimal(Double.valueOf(1.01)));
        System.out.println(TestMe.toImpliedDecimal(Double.valueOf(10.00)));
        System.out.println(TestMe.toImpliedDecimal(Double.valueOf(10.10)));

        System.out.println(TestMe.toMoney(null));
        System.out.println(TestMe.toMoney("1"));
        System.out.println(TestMe.toMoney("10"));
        System.out.println(TestMe.toMoney("1.01"));
        System.out.println(TestMe.toMoney("10.0"));
        System.out.println(TestMe.toMoney("10.10"));
    }

    /**
     * To implied decimal.
     *
     * @param value
     *            the value
     *
     * @return the string
     */
    public static String toImpliedDecimal(final Double value) {
        return value == null ? "0" : String.format("%.2f", value).replace(".", "");
    }

    /**
     * To money.
     *
     * @param value
     *            the value
     *
     * @return the string
     */
    public static String toMoney(final String value) {
        if (value != null && value.length() < 3) {
            return String.format("%.2f", Double.valueOf(Double.valueOf(value).doubleValue() / 100));
        }
        return value == null ? "0.00" : String.format("%.2f", Double.valueOf(value));
    }

}
