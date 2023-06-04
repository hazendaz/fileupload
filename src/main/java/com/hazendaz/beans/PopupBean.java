/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2018 Hazendaz.
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
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.Data;

@Data
@Named
@SessionScoped
public class PopupBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean showDuplicate;

    public void duplicate() {
        this.showDuplicate = !this.showDuplicate;
    }

    public boolean isShowDuplicate() {
        return this.showDuplicate;
    }

    /**
     * @param <T>
     *            Generic type for test string
     * @param testString
     *            Generic list test (not used)
     */
    public <T> void testExtends(final List<T> testString) {
        // TODO Test of generics
    }

}
