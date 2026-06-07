/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
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

/**
 * The Class StateCodesBean.
 */
@Named
@SessionScoped
public class StateCodesBean implements Serializable {

    /** The serial version uid. */
    private static final long serialVersionUID = 1L;

    /** The state codes. */
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

    /**
     * Add five.
     *
     * @param value
     *            the value
     *
     * @return the int
     */
    public int addFive(final int value) {
        return value + 5;
    }

    /**
     * Gets the state codes.
     *
     * @return the state codes
     */
    public List<SelectItem> getStateCodes() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Test Message from @GetStateCodes"));
        return StateCodesBean.stateCodes;
    }

}
