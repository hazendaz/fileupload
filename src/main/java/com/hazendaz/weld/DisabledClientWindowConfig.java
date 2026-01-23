/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
 */
package com.hazendaz.weld;

import jakarta.enterprise.inject.Specializes;
import jakarta.faces.context.FacesContext;

import org.apache.deltaspike.jsf.spi.scope.window.DefaultClientWindowConfig;

@Specializes
public class DisabledClientWindowConfig extends DefaultClientWindowConfig {

    private static final long serialVersionUID = 1L;

    @Override
    public ClientWindowRenderMode getClientWindowRenderMode(final FacesContext facesContext) {
        return ClientWindowRenderMode.NONE;
    }

}
