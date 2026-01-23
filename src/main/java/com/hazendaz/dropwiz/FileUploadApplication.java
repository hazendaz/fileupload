/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
 */
package com.hazendaz.dropwiz;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class FileUploadApplication extends Application<FileUploadConfiguration> {

    public static void main(String[] args) throws Exception {
        new FileUploadApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<FileUploadConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(FileUploadConfiguration configuration, Environment environment) {
        // nothing to do yet
    }

}
