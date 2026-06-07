/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.fileupload;

import com.hazendaz.servlets.RunnableTest;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;

import org.richfaces.event.FileUploadEvent;
// import org.richfaces.model.UploadedFile;
import org.slf4j.Logger;

/**
 * The Class FileUploadBean.
 */
@Named
@SessionScoped
public class FileUploadBean implements Serializable {

    /** The serial version uid. */
    private static final long serialVersionUID = 1L;

    /** The logger. */
    @Inject
    private Logger logger;

    /** The thread pool. */
    private ExecutorService threadPool;

    /** The files. */
    private ArrayList<UploadedImage> files = new ArrayList<>();

    /** The cancel. */
    private String cancel;

    /** The date. */
    private String date;

    /**
     * Cancel.
     *
     * @return the string
     */
    public String cancel() {
        this.setCancel();
        return this.getCancel();
    }

    /**
     * Clear upload data.
     *
     * @return the string
     */
    public String clearUploadData() {
        this.files.clear();
        return null;
    }

    /**
     * Delete.
     *
     * @return the string
     */
    public String delete() {
        final FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("The Delete Worked..."));
        return null;
    }

    /**
     * Gets the cancel.
     *
     * @return the cancel
     */
    public String getCancel() {
        return this.cancel;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Gets the files.
     *
     * @return the files
     */
    public ArrayList<UploadedImage> getFiles() {
        return this.files;
    }

    /**
     * Gets the now.
     *
     * @return the now
     */
    public String getNow() {
        final Calendar cal = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    /**
     * Gets the size.
     *
     * @return the size
     */
    public int getSize() {
        if (this.getFiles() != null) {
            return this.getFiles().size();
        }
        return 0;
    }

    /**
     * Gets the time stamp.
     *
     * @return the time stamp
     */
    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * Listener.
     *
     * @param event
     *            the event
     *
     * @throws Exception
     *             the exception
     */
    public void listener(final FileUploadEvent event) throws Exception {
        // TODO richfaces is still javax. Could replace this by using tomcat variation for fileupload from psi probe
        // final UploadedFile item = event.getUploadedFile();
        final UploadedImage file = new UploadedImage();
        // file.setLength(item.getData().length);
        // file.setName(item.getName());
        // file.setData(item.getData());
        this.files.add(file);
        this.threadPool = (ExecutorService) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
                .get("THREAD_POOL");
        final RunnableTest runnableTest = new RunnableTest();
        this.threadPool.execute(runnableTest);
    }

    /**
     * Paint.
     *
     * @param stream
     *            the stream
     * @param object
     *            the object
     *
     * @throws IOException
     *             the io exception
     */
    public void paint(final OutputStream stream, final Object object) throws IOException {
        stream.write(this.getFiles().get(((Integer) object).intValue()).getData());
        stream.close();
        this.logger.info("upload is finished");
    }

    /**
     * Set cancel.
     */
    public void setCancel() {
        this.cancel = "you have been cancelled by function 10";
    }

    /**
     * Sets the date.
     *
     * @param value
     *            the value
     */
    public void setDate(final String value) {
        this.date = value;
    }

    /**
     * Sets the files.
     *
     * @param value
     *            the value
     */
    public void setFiles(final ArrayList<UploadedImage> value) {
        this.files = value;
    }

    /**
     * @param now
     *            timestamp (not used)
     */
    public void setNow(final Timestamp now) {
        // do nothing
    }

}
