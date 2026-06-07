/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.beans;

import com.hazendaz.weld.AfterDeploy;
import com.hazendaz.weld.Eager;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;

import lombok.Getter;

/**
 * The Class ApplicationTest.
 */
@ApplicationScoped
@Eager
public class ApplicationTest implements Serializable {

    /** The serial version uid. */
    private static final long serialVersionUID = 1L;

    /** The found at startup. */
    @Getter
    private String foundAtStartup;

    /** The svn test. */
    @Getter
    private String svnTest;

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        this.foundAtStartup = "Yes";
    }

    /**
     * Init after.
     */
    @AfterDeploy
    public void initAfter() {
        this.foundAtStartup = "no";
    }

}
