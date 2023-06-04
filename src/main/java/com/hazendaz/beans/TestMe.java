/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2023 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
 */
package com.hazendaz.beans;

public class TestMe {

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

    public static String toImpliedDecimal(final Double value) {
        return value == null ? "0" : String.format("%.2f", value).replace(".", "");
    }

    public static String toMoney(final String value) {
        if (value != null && value.length() < 3) {
            return String.format("%.2f", Double.valueOf(Double.valueOf(value).doubleValue() / 100));
        }
        return value == null ? "0.00" : String.format("%.2f", Double.valueOf(value));
    }

}
