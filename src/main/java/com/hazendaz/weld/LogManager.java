/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.weld;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class LogManager.
 */
@ApplicationScoped
public class LogManager {

    /**
     * Get logger.
     *
     * @param injectionPoint
     *            the injection point
     *
     * @return the logger
     */
    @Produces
    public Logger getLogger(final InjectionPoint injectionPoint) {
        final String category = injectionPoint.getMember().getDeclaringClass().getName();
        return LoggerFactory.getLogger(category);
    }

}
