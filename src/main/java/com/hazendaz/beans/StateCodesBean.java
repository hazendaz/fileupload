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
package com.hazendaz.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

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
