/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
 */
package com.hazendaz.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.event.ValueChangeListener;
import jakarta.inject.Named;

@ApplicationScoped
@Named
public class MyValueChangeListener implements ValueChangeListener {
    @Override
    public void processValueChange(final ValueChangeEvent event) throws AbortProcessingException {
        System.out.println("Value changed");
    }
}
