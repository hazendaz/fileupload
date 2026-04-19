/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteManipulationTest {

    @Test
    void mainShouldPrintConvertedOutput() throws Exception {
        final PrintStream originalOut = System.out;
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
        try {
            ByteManipulation.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        final String text = output.toString(StandardCharsets.UTF_8);
        Assertions.assertTrue(text.contains("Hello World!"));
    }
}
