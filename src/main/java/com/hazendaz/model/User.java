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
package com.hazendaz.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

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
