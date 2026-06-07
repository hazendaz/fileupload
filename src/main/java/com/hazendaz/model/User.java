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

/**
 * The Class User.
 */
@SessionScoped
@Data
public class User implements IUser, Serializable {

    /** The serial version uid. */
    private static final long serialVersionUID = 1L;

    /** The name. */
    private String name;

    /**
     * Instantiates a new user.
     */
    public User() {
        // Do nothing
    }

    /**
     * Instantiates a new user.
     *
     * @param another
     *            the another
     */
    public User(final User another) {
        this.name = another.name; // you can access
    }

}
