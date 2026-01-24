/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.converters;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

@ApplicationScoped
@Named
@FacesConverter(forClass = String.class, managed = true)
public class UppercaseConverter implements Converter<Object> {

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
