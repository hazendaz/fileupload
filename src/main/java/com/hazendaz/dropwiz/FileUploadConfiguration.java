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

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.core.Configuration;

import jakarta.validation.constraints.NotEmpty;

public class FileUploadConfiguration extends Configuration {

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplate() {
        return this.template;
    }

    @JsonProperty
    public void setTemplate(String value) {
        this.template = value;
    }

    @JsonProperty
    public String getDefaultName() {
        return this.defaultName;
    }

    @JsonProperty
    public void setDefaultName(String value) {
        this.defaultName = value;
    }
}
