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
