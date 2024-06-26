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
package com.hazendaz.encryption;

import jakarta.inject.Inject;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Date;

import javax.crypto.Cipher;

import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.bcpg.PublicKeyAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.bcpg.sig.Features;
import org.bouncycastle.bcpg.sig.KeyFlags;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPKeyRingGenerator;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyEncryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.bc.BcPGPKeyPair;
import org.slf4j.Logger;

public class TestKeyGen {

    public final static PGPKeyRingGenerator generateKeyRingGenerator(final String id, final char[] pass,
            final int s2kcount) throws Exception {
        // This object generates individual key-pairs.
        final RSAKeyPairGenerator kpg = new RSAKeyPairGenerator();

        // Boilerplate RSA parameters, no need to change anything
        // except for the RSA key-size (2048). You can use whatever
        // key-size makes sense for you -- 4096, etc.
        kpg.init(new RSAKeyGenerationParameters(BigInteger.valueOf(0x10001), new SecureRandom(), 2048, 12));

        // First create the master (signing) key with the generator.
        final PGPKeyPair rsakp_sign = new BcPGPKeyPair(PublicKeyAlgorithmTags.RSA_SIGN, kpg.generateKeyPair(),
                new Date());
        // Then an encryption subkey.
        final PGPKeyPair rsakp_enc = new BcPGPKeyPair(PublicKeyAlgorithmTags.RSA_ENCRYPT, kpg.generateKeyPair(),
                new Date());

        // Add a self-signature on the id
        final PGPSignatureSubpacketGenerator signhashgen = new PGPSignatureSubpacketGenerator();

        // Add signed metadata on the signature.
        // 1) Declare its purpose
        signhashgen.setKeyFlags(false, KeyFlags.SIGN_DATA | KeyFlags.CERTIFY_OTHER);
        // 2) Set preferences for secondary crypto algorithms to use
        // when sending messages to this key.
        signhashgen.setPreferredSymmetricAlgorithms(false, new int[] { SymmetricKeyAlgorithmTags.AES_256,
                SymmetricKeyAlgorithmTags.AES_192, SymmetricKeyAlgorithmTags.AES_128 });
        signhashgen.setPreferredHashAlgorithms(false, new int[] { HashAlgorithmTags.SHA256, HashAlgorithmTags.SHA1,
                HashAlgorithmTags.SHA384, HashAlgorithmTags.SHA512, HashAlgorithmTags.SHA224, });
        // 3) Request senders add additional checksums to the
        // message (useful when verifying unsigned messages.)
        signhashgen.setFeature(false, Features.FEATURE_MODIFICATION_DETECTION);

        // Create a signature on the encryption subkey.
        final PGPSignatureSubpacketGenerator enchashgen = new PGPSignatureSubpacketGenerator();
        // Add metadata to declare its purpose
        enchashgen.setKeyFlags(false, KeyFlags.ENCRYPT_COMMS | KeyFlags.ENCRYPT_STORAGE);

        // Objects used to encrypt the secret key.
        final PGPDigestCalculator sha1Calc = new BcPGPDigestCalculatorProvider().get(HashAlgorithmTags.SHA1);
        final PGPDigestCalculator sha256Calc = new BcPGPDigestCalculatorProvider().get(HashAlgorithmTags.SHA256);

        // bcpg 1.48 exposes this API that includes s2kcount. Earlier
        // versions use a default of 0x60.
        final PBESecretKeyEncryptor pske = new BcPBESecretKeyEncryptorBuilder(SymmetricKeyAlgorithmTags.AES_256,
                sha256Calc, s2kcount).build(pass);

        // Finally, create the keyring itself. The constructor
        // takes parameters that allow it to generate the self
        // signature.
        final PGPKeyRingGenerator keyRingGen = new PGPKeyRingGenerator(PGPSignature.POSITIVE_CERTIFICATION, rsakp_sign,
                id, sha1Calc, signhashgen.generate(), null,
                new BcPGPContentSignerBuilder(rsakp_sign.getPublicKey().getAlgorithm(), HashAlgorithmTags.SHA1), pske);

        // Add our encryption subkey, together with its signature.
        keyRingGen.addSubKey(rsakp_enc, enchashgen.generate(), null);
        return keyRingGen;
    }

    public static void main(final String[] args) {

        final TestKeyGen testKeyGen = new TestKeyGen();

        final char password[] = { 't', 'e', 's', 't', 'm', 'e' };
        Security.addProvider(new BouncyCastleProvider());
        testKeyGen.logger.info(testKeyGen.createSecretKey("someuser@email.com", password).toString());
        testKeyGen.logger.info(testKeyGen.createPublicKey("someuser@email.com", password).toString());
    }

    @Inject
    private Logger logger;

    public PGPPublicKeyRing createPublicKey(final String email, final char[] password) {

        // Checks for max allowed value --- without fix in Java, it should say
        // 128.
        try {
            final int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
            this.logger.info("Allowed Key Length is: {}", String.valueOf(maxKeyLen));
        } catch (final NoSuchAlgorithmException e1) {
            this.logger.error(e1.toString());
        }

        KeyPair keyPair = null;
        KeyPair keyPair2 = null;
        PGPPublicKeyRing pkr = null;
        try {

            final KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", "BC");
            keyPairGen.initialize(4096);

            keyPair = keyPairGen.generateKeyPair();
            keyPair2 = keyPairGen.generateKeyPair();

            final AsymmetricKeyParameter privKey = PrivateKeyFactory.createKey(keyPair.getPrivate().getEncoded());
            final AsymmetricKeyParameter pubKey = PublicKeyFactory.createKey(keyPair2.getPublic().getEncoded());
            final AsymmetricCipherKeyPair privKeyParams = new AsymmetricCipherKeyPair(pubKey, privKey);

            final PGPSignatureSubpacketGenerator hashedGen = new PGPSignatureSubpacketGenerator();

            hashedGen.setKeyFlags(true,
                    KeyFlags.CERTIFY_OTHER | KeyFlags.SIGN_DATA | KeyFlags.ENCRYPT_COMMS | KeyFlags.ENCRYPT_STORAGE);

            hashedGen.setPreferredCompressionAlgorithms(false, new int[] { CompressionAlgorithmTags.ZIP });

            hashedGen.setPreferredHashAlgorithms(false, new int[] { HashAlgorithmTags.SHA1 });

            hashedGen.setPreferredSymmetricAlgorithms(false, new int[] { SymmetricKeyAlgorithmTags.AES_128 });

            final PGPKeyPair secretKey2 = new BcPGPKeyPair(PublicKeyAlgorithmTags.RSA_GENERAL, privKeyParams,
                    new Date());

            final PGPKeyRingGenerator keyRingGen = TestKeyGen
                    .generateKeyRingGenerator(email + "<" + email + "@hotmail.com>", password, 0xc0);

            keyRingGen.addSubKey(secretKey2);

            pkr = keyRingGen.generatePublicKeyRing();

        } catch (final PGPException e) {
            this.logger.error(e.toString());
            this.logger.error(e.getUnderlyingException().toString());
        } catch (final Exception e) {
            this.logger.error(e.toString());
        }
        return pkr;

    }

    // Note: s2kcount is a number between 0 and 0xff that controls the
    // number of times to iterate the password hash before use. More
    // iterations are useful against offline attacks, as it takes more
    // time to check each password. The actual number of iterations is
    // rather complex, and also depends on the hash function in use.
    // Refer to Section 3.7.1.3 in rfc4880.txt. Bigger numbers give
    // you more iterations. As a rough rule of thumb, when using
    // SHA256 as the hashing function, 0x10 gives you about 64
    // iterations, 0x20 about 128, 0x30 about 256 and so on till 0xf0,
    // or about 1 million iterations. The maximum you can go to is
    // 0xff, or about 2 million iterations. I'll use 0xc0 as a
    // default -- about 130,000 iterations.

    public PGPSecretKeyRing createSecretKey(final String email, final char[] password) {

        // Checks for max allowed value --- without fix in Java, it should say
        // 128.
        try {
            final int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
            this.logger.info("Allowed Key Length is: {}", String.valueOf(maxKeyLen));
        } catch (final NoSuchAlgorithmException e1) {
            this.logger.error(e1.toString());
        }

        KeyPair keyPair = null;
        KeyPair keyPair2 = null;
        PGPSecretKeyRing skr = null;
        try {

            final KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", "BC");
            keyPairGen.initialize(4096);

            keyPair = keyPairGen.generateKeyPair();
            keyPair2 = keyPairGen.generateKeyPair();

            final AsymmetricKeyParameter privKey = PrivateKeyFactory.createKey(keyPair.getPrivate().getEncoded());
            final AsymmetricKeyParameter pubKey = PublicKeyFactory.createKey(keyPair2.getPublic().getEncoded());
            final AsymmetricCipherKeyPair privKeyParams = new AsymmetricCipherKeyPair(pubKey, privKey);

            final PGPSignatureSubpacketGenerator hashedGen = new PGPSignatureSubpacketGenerator();

            hashedGen.setKeyFlags(true,
                    KeyFlags.CERTIFY_OTHER | KeyFlags.SIGN_DATA | KeyFlags.ENCRYPT_COMMS | KeyFlags.ENCRYPT_STORAGE);

            hashedGen.setPreferredCompressionAlgorithms(false, new int[] { CompressionAlgorithmTags.ZIP });

            hashedGen.setPreferredHashAlgorithms(false, new int[] { HashAlgorithmTags.SHA1 });

            hashedGen.setPreferredSymmetricAlgorithms(false, new int[] { SymmetricKeyAlgorithmTags.AES_128 });

            final PGPKeyPair secretKey2 = new BcPGPKeyPair(PublicKeyAlgorithmTags.RSA_GENERAL, privKeyParams,
                    new Date());

            final PGPKeyRingGenerator keyRingGen = TestKeyGen
                    .generateKeyRingGenerator(email + "<" + email + "@homail.com>", password, 0xc0);

            keyRingGen.addSubKey(secretKey2);

            skr = keyRingGen.generateSecretKeyRing();

        } catch (final PGPException e) {
            this.logger.error(e.toString());
            this.logger.error(e.getUnderlyingException().toString());
        } catch (final Exception e) {
            this.logger.error(e.toString());
        }
        return skr;

    }

}
