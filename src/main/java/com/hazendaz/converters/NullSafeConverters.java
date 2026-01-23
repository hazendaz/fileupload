/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
 */
package com.hazendaz.converters;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;

import java.util.ArrayList;

public class NullSafeConverters {

    public static void main(final String[] args) {
        final NullSafeConverters nullSafeConverters = new NullSafeConverters();
        System.out.println("'arrayList = " + nullSafeConverters.toString(new ArrayList<String>()) + "'\n");
        System.out.println("'null = " + nullSafeConverters.toString(null) + "'\n");
        System.out.println("'User Name = " + nullSafeConverters.toString("User Name") + "'\n");
        System.out.println("'55 = " + nullSafeConverters.toString(Integer.valueOf(55)) + "'\n");
        System.out.println("'55.0 = " + nullSafeConverters.toString(Double.valueOf(55.0)) + "'\n");

        System.out.println("'null = " + nullSafeConverters.isEmpty(null) + "'\n");
        System.out.println("'User Name = " + nullSafeConverters.isEmpty("User Name") + "'\n");
        System.out.println("'Spaces = " + nullSafeConverters.isEmpty("   ") + "'\n");
        System.out.println("'Space = " + nullSafeConverters.isEmpty(" ") + "'\n");
        System.out.println("'Empty = " + nullSafeConverters.isEmpty("") + "'\n");
        System.out.println("'55 = " + nullSafeConverters.isEmpty(Integer.valueOf(55)) + "'\n");
        System.out.println("'55.0 = " + nullSafeConverters.isEmpty(Double.valueOf(55.0)) + "'\n");
        System.out.println("'0 = " + nullSafeConverters.isEmpty(Integer.valueOf(0)) + "'\n");
        System.out.println("'Long of 0 = " + nullSafeConverters.isEmpty(Long.valueOf(0)) + "'\n");
        System.out.println("'Long 0 = " + nullSafeConverters.isEmpty(Long.valueOf(0L)) + "'\n");
        System.out.println("'Double of 0 = " + nullSafeConverters.isEmpty(Double.valueOf(0)) + "'\n");

        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "THIS_IS_AN_EXAMPLE_STRING") + '\n');
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "idevel_prod_servers"));

    }

    public <T> boolean isEmpty(final T value) {
        return value == null || value.equals("") || CharMatcher.whitespace().matchesAllOf(value.toString());
    }

    public <T extends Number> boolean isEmpty(final T value) {
        return value == null || value.equals(Integer.valueOf(0)) || value.equals(Long.valueOf(0L))
                || value.equals(Double.valueOf(0.0));
    }

    public <T> String toString(final T value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value);
    }

    // TODO This doesn't seem to be viable as it requires casting and lots of
    // checks...just use overloads.
    @SuppressWarnings("unchecked")
    public <T> T toType(final String value, final Class<T> type) {
        if (value == null) {
            return null;
        }
        if (type == Integer.class) {
            return (T) Integer.valueOf(value);
        }
        return null;
    }

}
