/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.model;

import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

import lombok.Data;

@SessionScoped
@Data
public class User implements IUser, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public User() {
        // Do nothing
    }

    public User(final User another) {
        this.name = another.name; // you can access
    }

}
