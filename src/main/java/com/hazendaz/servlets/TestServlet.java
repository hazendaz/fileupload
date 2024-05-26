/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2024 Hazendaz.
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

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServlet;

import org.slf4j.Logger;

public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger logger;

    @Override
    public void init() {
        this.logger.info("servlet started"); //$NON-NLS-1$
        this.logger.info("java endorsed directory: {}", System.getProperty("java.endorsed.dirs"));
    }

}
