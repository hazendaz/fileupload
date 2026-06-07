/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;
import org.slf4j.LoggerFactory;

/**
 * The Class LogonBeanTest.
 */
class LogonBeanTest {

    /** The logon bean. */
    LogonBean logonBean;

    /**
     * Clear test.
     */
    @Test
    void clearTest() {
        final LogonBean test = new LogonBean();
        Whitebox.setInternalState(this.logonBean, "logger", LoggerFactory.getLogger("testClass"));
        this.logonBean.clear();
        Assertions.assertEquals(test.getUserName(), this.logonBean.getUserName());
    }

    /**
     * Init.
     */
    @BeforeEach
    void init() {
        this.logonBean = new LogonBean();
    }

}
