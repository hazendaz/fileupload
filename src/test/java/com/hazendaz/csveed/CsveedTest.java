/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.csveed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.csveed.bean.BeanWriter;
import org.csveed.bean.BeanWriterImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * The Class CsveedTest.
 */
@Disabled
class CsveedTest {

    /**
     * The Class Bean.
     */
    public class Bean {
        /** The first. */
        private Integer first;
        /** The second. */
        private Long second;

        /**
         * Instantiates a new bean.
         *
         * @param first
         *            the first
         * @param second
         *            the second
         */
        public Bean(Integer first, Long second) {
            this.first = first;
            this.second = second;
        }

        /**
         * Gets the first.
         *
         * @return the first
         */
        public Integer getFirst() {
            return first;
        }

        /**
         * Sets the first.
         *
         * @param first
         *            the first
         */
        public void setFirst(Integer first) {
            this.first = first;
        }

        /**
         * Gets the second.
         *
         * @return the second
         */
        public Long getSecond() {
            return second;
        }

        /**
         * Sets the second.
         *
         * @param second
         *            the second
         */
        public void setSecond(Long second) {
            this.second = second;
        }
    }

    /**
     * Bug reason.
     */
    @Test
    void bugReason() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String value = numberFormat.format(6666);
        assertEquals("6,666", value);
    }

    /**
     * Bug solution.
     */
    @Test
    void bugSolution() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        numberFormat.setGroupingUsed(false);
        String value = numberFormat.format(6666);
        assertEquals("6666", value);
    }

    /**
     * Test locale.
     *
     * @throws IOException
     *             the io exception
     */
    @Test
    void testLocale() throws IOException {

        List<Bean> beans = new ArrayList<>();
        beans.add(new Bean(2222, 6666L));

        try (StringWriter writer = new StringWriter()) {

            BeanWriter<Bean> beanWriter = new BeanWriterImpl<>(writer, Bean.class);
            beanWriter.writeBeans(beans);
            writer.close();

            System.out.print(writer.getBuffer().toString());
            assertEquals("\"first\";\"second\"\r\n" + "\"2222\";\"6666\"\r\n", writer.getBuffer().toString());
        }

    }
}
