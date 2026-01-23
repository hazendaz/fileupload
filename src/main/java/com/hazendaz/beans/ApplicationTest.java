/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
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
