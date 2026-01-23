/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.model;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@ApplicationScoped
@Data
public class UserList implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<User> userList;

    public UserList() {
        this.userList = new ArrayList<>();
    }
}
