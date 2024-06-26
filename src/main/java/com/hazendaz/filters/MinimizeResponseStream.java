/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2024 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
 */
package com.hazendaz.filters;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MinimizeResponseStream extends ServletOutputStream {

    private DataOutputStream output;

    public MinimizeResponseStream(final OutputStream value) {
        this.output = new DataOutputStream(value);
    }

    @Override
    public void write(final byte[] arg0) throws IOException {
        this.output.write(arg0);
    }

    @Override
    public void write(final byte[] arg0, final int arg1, final int arg2) throws IOException {
        this.output.write(arg0, arg1, arg2);
    }

    @Override
    public void write(final int arg0) throws IOException {
        this.output.write(arg0);
    }

    @Override
    public boolean isReady() {
        // TODO Do nothing for now
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        // TODO Do nothing for now
    }

}
