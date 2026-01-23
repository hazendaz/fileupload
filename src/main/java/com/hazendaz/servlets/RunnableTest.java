/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableTest implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(RunnableTest.class);

    @Override
    public void run() {
        for (int x = 9999; --x >= 0;) {
            this.logger.error("{}", String.valueOf(x));
        }

    }

}
