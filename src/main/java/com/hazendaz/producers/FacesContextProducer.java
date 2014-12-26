package com.hazendaz.producers;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextProducer {

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
