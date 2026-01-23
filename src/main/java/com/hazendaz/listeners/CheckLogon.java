/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
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
