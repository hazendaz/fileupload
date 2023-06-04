/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2014 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
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
