/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2025 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
 */
package com.hazendaz.aes;

import java.nio.charset.StandardCharsets;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Base64;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AesTest {

    public static void main(final String[] args) throws Exception {
        final AesTest aesTest = new AesTest();
        final String encodedData = aesTest.encode("Some Name");
        final String decodedData = aesTest.decode(encodedData);
        aesTest.logger.info("My password is: {}", decodedData);
    }

    private static byte[] cipherData(final PaddedBufferedBlockCipher cipher, final byte[] data) throws Exception {
        final int minSize1 = cipher.getOutputSize(data.length);
        final byte[] outBuf = new byte[minSize1];
        final int length1 = cipher.processBytes(data, 0, data.length, outBuf, 0);
        final int length2 = cipher.doFinal(outBuf, length1);
        final int actualLength = length1 + length2;
        return new byte[actualLength];
    }

    private static byte[] decrypt(final byte[] cipher, final byte[] key, final byte[] iv) throws Exception {
        final PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(
                CBCBlockCipher.newInstance(AESEngine.newInstance()));
        final CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
        aes.init(false, ivAndKey);
        return AesTest.cipherData(aes, cipher);
    }

    private static byte[] encrypt(final byte[] plain, final byte[] key, final byte[] iv) throws Exception {
        final PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(
                CBCBlockCipher.newInstance(AESEngine.newInstance()));
        final CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
        aes.init(true, ivAndKey);
        return AesTest.cipherData(aes, plain);
    }

    private final Logger logger = LoggerFactory.getLogger(AesTest.class);

    private final byte[] key = "KEY STRING LENTH OF THIRTYTWO 32".getBytes(StandardCharsets.UTF_8);

    private final byte[] iv = "company/departme".getBytes(StandardCharsets.UTF_8);

    public String decode(final String encodedText) throws Exception {
        return new String(
                AesTest.decrypt(Base64.decode(encodedText.getBytes(StandardCharsets.UTF_8)), this.key, this.iv),
                StandardCharsets.UTF_8);
    }

    public String encode(final String plainText) throws Exception {
        return new String(Base64.encode(AesTest.encrypt(plainText.getBytes(StandardCharsets.UTF_8), this.key, this.iv)),
                StandardCharsets.UTF_8);
    }

    @Test
    public void userIsHappy() {
        System.out.print("User " + "ABC is Happy".substring(4));
    }

}
