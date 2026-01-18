/*
 * fileupload (https://github.com/hazendaz/fileupload)
 *
 * Copyright 2009-2026 Hazendaz.
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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;
import org.slf4j.LoggerFactory;

class LogonBeanTest {

    LogonBean logonBean;

    @Test
    void clearTest() {
        final LogonBean test = new LogonBean();
        Whitebox.setInternalState(this.logonBean, "logger", LoggerFactory.getLogger("testClass"));
        this.logonBean.clear();
        Assertions.assertEquals(test.getUserName(), this.logonBean.getUserName());
    }

    @BeforeEach
    void init() {
        this.logonBean = new LogonBean();
    }

}
