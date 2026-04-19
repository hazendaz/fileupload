/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.fileupload;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UploadedImageTest {

    @Test
    void shouldStoreBinaryDataAndLength() {
        final UploadedImage image = new UploadedImage();
        final byte[] data = new byte[] { 1, 2, 3 };

        image.setData(data);
        image.setLength(data.length);

        Assertions.assertArrayEquals(data, image.getData());
        Assertions.assertEquals(3, image.getLength());
    }

    @Test
    void setNameShouldSetCsvMimeTypeForCsvExtension() {
        final UploadedImage image = new UploadedImage();

        image.setName("data.csv");

        Assertions.assertEquals("text/csv", image.getMime());
    }

    @Test
    void setNameShouldSetUnknownMimeTypeForOtherExtensions() {
        final UploadedImage image = new UploadedImage();

        image.setName("data.txt");

        Assertions.assertEquals("text/unknown", image.getMime());
    }

    @Test
    void setNameShouldLeaveMimeUnchangedWhenNoExtension() {
        final UploadedImage image = new UploadedImage();

        image.setName("filename");

        Assertions.assertNull(image.getMime());
    }
}
