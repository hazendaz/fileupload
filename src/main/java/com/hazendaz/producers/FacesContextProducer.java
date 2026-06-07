/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.producers;

import jakarta.enterprise.context.ContextNotActiveException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.context.FacesContext;

/**
 * The Class FacesContextProducer.
 */
public class FacesContextProducer {

    /**
     * Gets the faces context.
     *
     * @return the faces context
     */
    @Produces
    @RequestScoped
    public FacesContext getFacesContext() {
        final FacesContext context = FacesContext.getCurrentInstance();
        if (context == null) {
            throw new ContextNotActiveException("FacesContext is not active");
        }
        return context;
    }
}
