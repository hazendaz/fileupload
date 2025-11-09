/*
 * fileupload (https://github.com/hazendaz/fileupload)
 *
 * Copyright 2009-2025 Hazendaz.
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

import org.apache.deltaspike.core.spi.activation.ClassDeactivator;
import org.apache.deltaspike.core.spi.activation.Deactivatable;
import org.apache.deltaspike.jsf.impl.listener.system.JsfSystemEventBroadcaster;

public class JsfSystemEventBroadcasterDeactivator implements ClassDeactivator {

    private static final long serialVersionUID = 1L;

    @Override
    public Boolean isActivated(final Class<? extends Deactivatable> targetClass) {
        // Deactivate ExcludeExtension
        if (JsfSystemEventBroadcaster.class.equals(targetClass)) {
            return Boolean.FALSE;
        }
        // No information about other extensions
        return null;
    }

}
