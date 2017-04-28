package com.hazendaz.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@ApplicationScoped
@Named
@FacesConverter(forClass = String.class)
public class UppercaseConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext facesContext, final UIComponent component, final String stringValue) {

        if (stringValue == null) {
            return stringValue;
        }
        if (!component.getId().equalsIgnoreCase("notUpperCased")) {
            return stringValue.toUpperCase();
        }
        return stringValue;
    }

    @Override
    public String getAsString(final FacesContext facesContext, final UIComponent uiComponent,
            final Object objectValue) {

        if (objectValue == null) {
            return null;
        }
        return objectValue.toString();
    }

}
