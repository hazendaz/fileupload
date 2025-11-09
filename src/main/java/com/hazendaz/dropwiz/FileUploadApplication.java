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
