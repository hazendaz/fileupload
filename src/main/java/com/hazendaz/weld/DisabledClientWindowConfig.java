package com.hazendaz.weld;

import javax.enterprise.inject.Specializes;
import javax.faces.context.FacesContext;

import org.apache.deltaspike.jsf.spi.scope.window.DefaultClientWindowConfig;

@Specializes
public class DisabledClientWindowConfig extends DefaultClientWindowConfig {

    private static final long serialVersionUID = 1L;

    @Override
    public ClientWindowRenderMode getClientWindowRenderMode(final FacesContext facesContext) {
        return ClientWindowRenderMode.NONE;
    }

}
