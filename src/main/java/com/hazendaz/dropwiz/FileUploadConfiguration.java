/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
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
