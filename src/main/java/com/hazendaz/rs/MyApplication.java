/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.rs;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * The Class MyApplication.
 */
public class MyApplication extends ResourceConfig {
    /**
     * Instantiates a new my application.
     */
    public MyApplication() {
        this.register(new WebServiceBinder());
        this.packages(true, "com.hazendaz.rs");
    }
}
