/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
 */
package com.hazendaz.converters;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

import java.text.DecimalFormat;

@ApplicationScoped
@Named
@FacesConverter(value = "moneyConverter", managed = true)
public class MoneyConverter implements Converter<Object> {

    @Override
    public Object getAsObject(final FacesContext facesContext, final UIComponent component, final String stringValue) {
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(stringValue);
    }

    @Override
    public String getAsString(final FacesContext facesContext, final UIComponent uiComponent,
            final Object objectValue) {
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(objectValue);
    }

}
