/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.fileupload;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

class FileUploadBeanTest {

    @Test
    void cancelShouldSetAndReturnCancelMessage() {
        final FileUploadBean bean = new FileUploadBean();

        final String result = bean.cancel();

        Assertions.assertEquals("you have been cancelled by function 10", result);
        Assertions.assertEquals(result, bean.getCancel());
    }

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

    @Test
    void getSizeShouldReturnZeroWhenFilesIsNull() {
        final FileUploadBean bean = new FileUploadBean();
        bean.setFiles(null);

        Assertions.assertEquals(0, bean.getSize());
    }

    @Test
    void getNowAndTimeStampShouldReturnCurrentValues() {
        final FileUploadBean bean = new FileUploadBean();

        final String now = bean.getNow();
        final long timestamp = bean.getTimeStamp();

        Assertions.assertTrue(now.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
        Assertions.assertTrue(timestamp > 0);
    }

    @Test
    void setNowShouldBeNoOp() {
        final FileUploadBean bean = new FileUploadBean();

        bean.setNow(new Timestamp(System.currentTimeMillis()));

        Assertions.assertNull(bean.getDate());
    }

    @Test
    void paintShouldWriteFileBytesToOutputStream() throws Exception {
        final FileUploadBean bean = new FileUploadBean();
        final UploadedImage image = new UploadedImage();
        image.setData(new byte[] { 4, 5, 6 });
        bean.setFiles(new ArrayList<>());
        bean.getFiles().add(image);
        final Field loggerField = FileUploadBean.class.getDeclaredField("logger");
        loggerField.setAccessible(true);
        loggerField.set(bean, LoggerFactory.getLogger(FileUploadBeanTest.class));
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        bean.paint(outputStream, Integer.valueOf(0));

        Assertions.assertArrayEquals(new byte[] { 4, 5, 6 }, outputStream.toByteArray());
    }
}
