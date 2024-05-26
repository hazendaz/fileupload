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
package com.hazendaz.listeners;

import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

public class CheckLogon implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(final PhaseEvent arg0) {
        // Do nothing now
    }

    @Override
    public void beforePhase(final PhaseEvent arg0) {
        // Do nothing now
    }

    @Override
    public PhaseId getPhaseId() {
        return null;
    }

}
