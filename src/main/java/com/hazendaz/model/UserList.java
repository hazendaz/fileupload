/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.model;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * The Class UserList.
 */
@ApplicationScoped
@Data
public class UserList implements Serializable {

    /** The serial version uid. */
    private static final long serialVersionUID = 1L;

    /** The user list. */
    private List<User> userList;

    /**
     * Instantiates a new user list.
     */
    public UserList() {
        this.userList = new ArrayList<>();
    }
}
