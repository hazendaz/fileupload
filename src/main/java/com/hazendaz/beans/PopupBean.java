/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

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
