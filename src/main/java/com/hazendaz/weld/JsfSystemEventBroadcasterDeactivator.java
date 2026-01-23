/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
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
