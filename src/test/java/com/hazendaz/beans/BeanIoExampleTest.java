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
 * The Class BeanIoExampleTest.
 */
class BeanIoExampleTest {

    /** The bean io example. */
    BeanIoExample beanIoExample = new BeanIoExample();

    /**
     * Csv example.
     */
    @Test
    void csvExample() {
        Assertions.assertEquals(2, this.beanIoExample.csvExample());
    }

}
