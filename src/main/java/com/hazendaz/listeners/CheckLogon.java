package com.hazendaz.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

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
