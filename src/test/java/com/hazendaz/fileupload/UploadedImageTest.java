/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.fileupload;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The Class UploadedImageTest.
 */
class UploadedImageTest {

    /**
     * Should store binary data and length.
     */
    @Test
    void shouldStoreBinaryDataAndLength() {
        final UploadedImage image = new UploadedImage();
        final byte[] data = new byte[] { 1, 2, 3 };

        image.setData(data);
        image.setLength(data.length);

        Assertions.assertArrayEquals(data, image.getData());
        Assertions.assertEquals(3, image.getLength());
    }

    /**
     * Set name should set csv mime type for csv extension.
     */
    @Test
    void setNameShouldSetCsvMimeTypeForCsvExtension() {
        final UploadedImage image = new UploadedImage();

        image.setName("data.csv");

        Assertions.assertEquals("text/csv", image.getMime());
    }

    /**
     * Set name should set unknown mime type for other extensions.
     */
    @Test
    void setNameShouldSetUnknownMimeTypeForOtherExtensions() {
        final UploadedImage image = new UploadedImage();

        image.setName("data.txt");

        Assertions.assertEquals("text/unknown", image.getMime());
    }

    /**
     * Set name should leave mime unchanged when no extension.
     */
    @Test
    void setNameShouldLeaveMimeUnchangedWhenNoExtension() {
        final UploadedImage image = new UploadedImage();

        image.setName("filename");

        Assertions.assertNull(image.getMime());
    }
}
