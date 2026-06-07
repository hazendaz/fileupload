/*
 * SPDX-License-Identifier: Apache-2.0
 * See LICENSE file for details.
 *
 * Copyright 2009-2026 Hazendaz
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

/**
 * The Class AesTest2.
 */
@SessionScoped
public class AesTest2 implements Serializable {

    /** The serial version uid. */
    private static final long serialVersionUID = 1L;

    /**
     * Main.
     *
     * @param args
     *            the args
     *
     * @throws Exception
     *             the exception
     */
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

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger(AesTest2.class);

    /** The aes cipher. */
    private final BlockCipher AESCipher = AESEngine.newInstance();

    /** The cipher. */
    private PaddedBufferedBlockCipher cipher;
    /** The key parameter. */
    private KeyParameter keyParameter;

    /**
     * Decrypt.
     *
     * @param input
     *            the input
     *
     * @return the byte[]
     *
     * @throws DataLengthException
     *             the data length exception
     * @throws InvalidCipherTextException
     *             the invalid cipher text exception
     */
    public byte[] decrypt(final byte[] input) throws DataLengthException, InvalidCipherTextException {
        return this.processing(input, false);
    }

    /**
     * Encrypt.
     *
     * @param input
     *            the input
     *
     * @return the byte[]
     *
     * @throws DataLengthException
     *             the data length exception
     * @throws InvalidCipherTextException
     *             the invalid cipher text exception
     */
    public byte[] encrypt(final byte[] input) throws DataLengthException, InvalidCipherTextException {
        return this.processing(input, true);
    }

    /**
     * Init.
     */
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

    /**
     * Processing.
     *
     * @param input
     *            the input
     * @param encrypt
     *            the encrypt
     *
     * @return the byte[]
     *
     * @throws DataLengthException
     *             the data length exception
     * @throws InvalidCipherTextException
     *             the invalid cipher text exception
     */
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
