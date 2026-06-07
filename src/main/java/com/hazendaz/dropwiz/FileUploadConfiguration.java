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

/**
 * The Class FileUploadConfiguration.
 */
public class FileUploadConfiguration extends Configuration {

    /** The template. */
    @NotEmpty
    private String template;

    /** The default name. */
    @NotEmpty
    private String defaultName = "Stranger";

    /**
     * Gets the template.
     *
     * @return the template
     */
    @JsonProperty
    public String getTemplate() {
        return this.template;
    }

    /**
     * Sets the template.
     *
     * @param value
     *            the value
     */
    @JsonProperty
    public void setTemplate(String value) {
        this.template = value;
    }

    /**
     * Gets the default name.
     *
     * @return the default name
     */
    @JsonProperty
    public String getDefaultName() {
        return this.defaultName;
    }

    /**
     * Sets the default name.
     *
     * @param value
     *            the value
     */
    @JsonProperty
    public void setDefaultName(String value) {
        this.defaultName = value;
    }
}
