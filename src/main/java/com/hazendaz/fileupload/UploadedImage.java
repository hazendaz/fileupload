/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2025 Hazendaz
 */
package com.hazendaz.fileupload;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class UploadedImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;
    private String mime;
    private long length;
    private byte[] data;

    public byte[] getData() {
        return this.data;
    }

    public long getLength() {
        return this.length;
    }

    public String getMime() {
        return this.mime;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public void setData(final byte[] value) {
        this.data = value;
    }

    public void setLength(final long value) {
        this.length = value;
    }

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
