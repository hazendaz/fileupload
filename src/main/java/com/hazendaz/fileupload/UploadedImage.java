/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.fileupload;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * The Class UploadedImage.
 */
public class UploadedImage implements Serializable {

    /** The serial version uid. */
    private static final long serialVersionUID = 1L;

    /** The name. */
    @NotNull
    private String name;
    /** The mime. */
    private String mime;
    /** The length. */
    private long length;
    /** The data. */
    private byte[] data;

    /**
     * Gets the data.
     *
     * @return the data
     */
    public byte[] getData() {
        return this.data;
    }

    /**
     * Gets the length.
     *
     * @return the length
     */
    public long getLength() {
        return this.length;
    }

    /**
     * Gets the mime.
     *
     * @return the mime
     */
    public String getMime() {
        return this.mime;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @NotNull
    public String getName() {
        return this.name;
    }

    /**
     * Sets the data.
     *
     * @param value
     *            the value
     */
    public void setData(final byte[] value) {
        this.data = value;
    }

    /**
     * Sets the length.
     *
     * @param value
     *            the value
     */
    public void setLength(final long value) {
        this.length = value;
    }

    /**
     * Sets the name.
     *
     * @param value
     *            the value
     */
    public void setName(final String value) {
        final int extDot = value.lastIndexOf('.');
        if (extDot > 0) {
            final String extension = value.substring(extDot + 1);
            if ("csv".equals(extension)) {
                this.mime = "text/csv";
            } else {
                this.mime = "text/unknown";
            }
        }
    }
}
