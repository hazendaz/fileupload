/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.fileupload;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The Class FileUploadBeanTest.
 */
class FileUploadBeanTest {

    /**
     * Cancel should set and return cancel message.
     */
    @Test
    void cancelShouldSetAndReturnCancelMessage() {
        final FileUploadBean bean = new FileUploadBean();

        final String result = bean.cancel();

        Assertions.assertEquals("you have been cancelled by function 10", result);
        Assertions.assertEquals(result, bean.getCancel());
    }

    /**
     * Clear upload data should empty files list.
     */
    @Test
    void clearUploadDataShouldEmptyFilesList() {
        final FileUploadBean bean = new FileUploadBean();
        final ArrayList<UploadedImage> files = new ArrayList<>();
        files.add(new UploadedImage());
        bean.setFiles(files);

        final String result = bean.clearUploadData();

        Assertions.assertNull(result);
        Assertions.assertEquals(0, bean.getSize());
    }

    /**
     * Getters and setters should work for date and files.
     */
    @Test
    void gettersAndSettersShouldWorkForDateAndFiles() {
        final FileUploadBean bean = new FileUploadBean();
        final ArrayList<UploadedImage> files = new ArrayList<>();
        files.add(new UploadedImage());

        bean.setDate("2026-01-01");
        bean.setFiles(files);

        Assertions.assertEquals("2026-01-01", bean.getDate());
        Assertions.assertSame(files, bean.getFiles());
        Assertions.assertEquals(1, bean.getSize());
    }

    /**
     * Gets the size should return zero when files is null.
     */
    @Test
    void getSizeShouldReturnZeroWhenFilesIsNull() {
        final FileUploadBean bean = new FileUploadBean();
        bean.setFiles(null);

        Assertions.assertEquals(0, bean.getSize());
    }

    /**
     * Gets the now and time stamp should return current values.
     */
    @Test
    void getNowAndTimeStampShouldReturnCurrentValues() {
        final FileUploadBean bean = new FileUploadBean();

        final String now = bean.getNow();
        final long timestamp = bean.getTimeStamp();

        Assertions.assertTrue(now.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
        Assertions.assertTrue(timestamp > 0);
    }

    /**
     * Set now should be no op.
     */
    @Test
    void setNowShouldBeNoOp() {
        final FileUploadBean bean = new FileUploadBean();

        bean.setNow(new Timestamp(System.currentTimeMillis()));

        Assertions.assertNull(bean.getDate());
    }

    /**
     * Paint should write file bytes to output stream.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    void paintShouldWriteFileBytesToOutputStream() throws Exception {
        final FileUploadBean bean = new FileUploadBean();
        final UploadedImage image = new UploadedImage();
        image.setData(new byte[] { 4, 5, 6 });
        bean.setFiles(new ArrayList<>());
        bean.getFiles().add(image);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Assertions.assertThrows(NullPointerException.class, () -> bean.paint(outputStream, Integer.valueOf(0)));

        Assertions.assertArrayEquals(new byte[] { 4, 5, 6 }, outputStream.toByteArray());
    }
}
