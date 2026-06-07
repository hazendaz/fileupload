/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServlet;

import org.slf4j.Logger;

/**
 * The Class TestServlet.
 */
public class TestServlet extends HttpServlet {

    /** The serial version uid. */
    private static final long serialVersionUID = 1L;

    /** The logger. */
    @Inject
    private Logger logger;

    @Override
    public void init() {
        this.logger.info("servlet started"); //$NON-NLS-1$
        this.logger.info("java endorsed directory: {}", System.getProperty("java.endorsed.dirs"));
    }

}
