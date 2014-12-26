package com.hazendaz.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.inject.Named;

@ApplicationScoped
@Named
public class MyValueChangeListener implements ValueChangeListener {
    @Override
    public void processValueChange(final ValueChangeEvent event) throws AbortProcessingException {
        System.out.println("Value changed");
    }
}
