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
