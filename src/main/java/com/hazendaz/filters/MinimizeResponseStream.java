package com.hazendaz.filters;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

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
