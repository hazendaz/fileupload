/*
 * fileupload (https://github.com/hazendaz/fileupload)
 *
 * Copyright 2009-2025 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
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
