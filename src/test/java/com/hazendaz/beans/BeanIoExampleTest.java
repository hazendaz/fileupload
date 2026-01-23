/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BeanIoExampleTest {

    BeanIoExample beanIoExample = new BeanIoExample();

    @Test
    void csvExample() {
        Assertions.assertEquals(2, this.beanIoExample.csvExample());
    }

}
