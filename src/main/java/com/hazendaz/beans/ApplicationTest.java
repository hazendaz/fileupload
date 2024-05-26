/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2024 Hazendaz.
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

import com.hazendaz.weld.AfterDeploy;
import com.hazendaz.weld.Eager;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;

import lombok.Getter;

@ApplicationScoped
@Eager
public class ApplicationTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private String foundAtStartup;

    @Getter
    private String svnTest;

    @PostConstruct
    public void init() {
        this.foundAtStartup = "Yes";
    }

    @AfterDeploy
    public void initAfter() {
        this.foundAtStartup = "no";
    }

}
