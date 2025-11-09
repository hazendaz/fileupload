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
package com.hazendaz.aes;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
public class AesTest2 implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void main(final String[] args) throws Exception {

        final AesTest2 abc = new AesTest2();
        abc.init();

        final String secret = "User is Cool";
        System.out.println(secret);

        final byte[] encr = abc.encrypt(secret.getBytes(StandardCharsets.UTF_8));
        final byte[] decr = abc.decrypt(encr);

        final String decrypted = new String(decr, StandardCharsets.UTF_8);
        System.out.println(decrypted);
    }

    private final Logger logger = LoggerFactory.getLogger(AesTest2.class);

    private final BlockCipher AESCipher = AESEngine.newInstance();

    private PaddedBufferedBlockCipher cipher;
    private KeyParameter keyParameter;

    public byte[] decrypt(final byte[] input) throws DataLengthException, InvalidCipherTextException {
        return this.processing(input, false);
    }

    public byte[] encrypt(final byte[] input) throws DataLengthException, InvalidCipherTextException {
        return this.processing(input, true);
    }

    @PostConstruct
    public void init() {
        KeyGenerator kg;
        try {
            kg = KeyGenerator.getInstance("AES", new BouncyCastleProvider());
        } catch (final NoSuchAlgorithmException e) {
            this.logger.error("{}", e.getMessage());
            return;
        }
        kg.init(256, new SecureRandom());
        this.setPadding(new PKCS7Padding());
        this.setKey(kg.generateKey().getEncoded());
    }

    /**
     * @param key
     *            is the parameter key to set.
     */
    public void setKey(final byte[] key) {
        this.keyParameter = new KeyParameter(key);
    }

    /**
     * @param cipherPadding
     *            is the chipher padding to set.
     */
    public void setPadding(final BlockCipherPadding cipherPadding) {
        this.cipher = new PaddedBufferedBlockCipher(this.AESCipher, cipherPadding);
    }

    private byte[] processing(final byte[] input, final boolean encrypt)
            throws DataLengthException, InvalidCipherTextException {
        this.cipher.init(encrypt, this.keyParameter);

        final byte[] output = new byte[this.cipher.getOutputSize(input.length)];
        int bytesWrittenOut = this.cipher.processBytes(input, 0, input.length, output, 0);
        bytesWrittenOut += this.cipher.doFinal(output, bytesWrittenOut);

        if (encrypt) {
            return output;
        }
        System.arraycopy(output, 0, new byte[bytesWrittenOut], 0, bytesWrittenOut);
        return output;
    }
}
