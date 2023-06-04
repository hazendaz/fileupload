/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2023 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
 */
package com.hazendaz.converters;

import java.text.DecimalFormat;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

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
