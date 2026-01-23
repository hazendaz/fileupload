/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
 */
package com.hazendaz.weld;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class LogManager {

    @Produces
    public Logger getLogger(final InjectionPoint injectionPoint) {
        final String category = injectionPoint.getMember().getDeclaringClass().getName();
        return LoggerFactory.getLogger(category);
    }

}
