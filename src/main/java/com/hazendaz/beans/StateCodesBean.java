/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

@Named
@SessionScoped
public class StateCodesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static List<SelectItem> stateCodes;

    static {
        final PropertyResourceBundle properties = (PropertyResourceBundle) ResourceBundle.getBundle("StateCodes");
        final Enumeration<String> keys = properties.getKeys();
        StateCodesBean.stateCodes = new ArrayList<>();
        String nextKey = null;
        while (keys.hasMoreElements()) {
            nextKey = keys.nextElement();
            StateCodesBean.stateCodes.add(new SelectItem(nextKey, properties.getString(nextKey)));
        }
    }

    public int addFive(final int value) {
        return value + 5;
    }

    public List<SelectItem> getStateCodes() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Test Message from @GetStateCodes"));
        return StateCodesBean.stateCodes;
    }

}
