/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.encryption;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Security;
import java.util.Iterator;

import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.bc.BcPGPObjectFactory;
import org.bouncycastle.openpgp.bc.BcPGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleSignOnTest {

    private static Logger logger = LoggerFactory.getLogger(SingleSignOnTest.class);

    // private static File publicKeyFile = new File(
    // "/Development/Java/Single Sign On with Encryption(PGP)/PGP1D0.pkr");
    // private static File privateKeyFile = new File(
    // "/Development/Java/Single Sign On with Encryption(PGP)/PGP1D0.skr");
    private static String privateKeyPassword = "passphrase";

    //
    // Public class method decrypt
    //
    public static String decrypt(final byte[] encdata) {

        SingleSignOnTest.logger.info("decrypt(): data length = {}", Integer.valueOf(encdata.length));
        // ----- Decrypt the file
        try {
            final ByteArrayInputStream bais = new ByteArrayInputStream(encdata);
            // FileInputStream privKey = new FileInputStream(privateKeyFile);
            final TestKeyGen testKeyGen = new TestKeyGen();
            final PGPSecretKey key = testKeyGen.createSecretKey("someuser@email.com", "testme".toCharArray())
                    .getSecretKey();
            String result;
            try (InputStream privKey = Files.newInputStream(Path.of(key.toString()))) {
                result = SingleSignOnTest._decrypt(bais, privKey, SingleSignOnTest.privateKeyPassword.toCharArray());
            }
            return result;
        } catch (final Exception e) {
            SingleSignOnTest.logger.error(e.getMessage());
            SingleSignOnTest.logger.error(e.toString());
        }
        return null;

    }

    //
    // Public class method encrypt
    //
    public static byte[] encrypt(final byte[] data) {

        try {
            // ----- Read in the public key
            // PGPPublicKey key = readPublicKeyFromCol(new FileInputStream(
            // publicKeyFile));
            final TestKeyGen testKeyGen = new TestKeyGen();
            final PGPPublicKey key = testKeyGen.createPublicKey("someuser@email.com", "testme".toCharArray())
                    .getPublicKey();
            SingleSignOnTest.logger.info("Creating a temp file...");
            // create a file and write the string to it
            final File tempfile = File.createTempFile("pgp", null);
            try (OutputStream fos = Files.newOutputStream(tempfile.toPath())) {
                fos.write(data);
            }
            SingleSignOnTest.logger.info("Temp file created at ");
            SingleSignOnTest.logger.info(tempfile.getAbsolutePath());
            SingleSignOnTest.logger
                    .info("Reading the temp file to make sure that the bits were written\n--------------");
            try (BufferedReader isr = Files.newBufferedReader(tempfile.toPath(), StandardCharsets.UTF_8)) {
                String line = "";
                while ((line = isr.readLine()) != null) {
                    SingleSignOnTest.logger.info(line + "\n");
                }
            }
            // find out a little about the keys in the public key ring
            System.out.println("Key Strength = " + key.getBitStrength());
            System.out.println("Algorithm = " + key.getAlgorithm());
            System.out.println("Bit strength = " + key.getBitStrength());
            System.out.println("Version = " + key.getVersion());
            System.out.println("Encryption key = " + key.isEncryptionKey() + ", Master key = " + key.isMasterKey());
            int count = 0;
            for (final Iterator<?> iterator = key.getUserIDs(); iterator.hasNext();) {
                count++;
                System.out.println((String) iterator.next());
            }
            SingleSignOnTest.logger.info("Key Count = " + count);
            // create an armored ascii file
            // FileOutputStream out = new FileOutputStream(outputfile);
            // encrypt the file
            // encryptFile(tempfile.getAbsolutePath(), out, key);
            // Encrypt the data
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            SingleSignOnTest._encrypt(tempfile.getAbsolutePath(), baos, key);
            System.out.println("encrypted text length=" + baos.size());
            tempfile.delete();
            return baos.toByteArray();
        } catch (final PGPException e) {
            SingleSignOnTest.logger.error(e.getUnderlyingException().toString());
            SingleSignOnTest.logger.error(e.toString());
        } catch (final Exception e) {
            SingleSignOnTest.logger.error(e.toString());
        }
        return null;

    }

    // //
    // // Private class method readPublicKeyFromCol
    // //
    // private static PGPPublicKey readPublicKeyFromCol(InputStream in)
    // throws Exception {
    //
    // PGPPublicKeyRing pkRing = null;
    // PGPPublicKeyRingCollection pkCol = new PGPPublicKeyRingCollection(in);
    // System.out.println("key ring size=" + pkCol.size());
    // Iterator<?> it = pkCol.getKeyRings();
    // while (it.hasNext()) {
    // pkRing = (PGPPublicKeyRing) it.next();
    // Iterator<?> pkIt = pkRing.getPublicKeys();
    // while (pkIt.hasNext()) {
    // PGPPublicKey key = (PGPPublicKey) pkIt.next();
    // SingleSignOnTest.logger.info(
    // "Encryption key = {}, Master key = {}",
    // key.isEncryptionKey(), key.isMasterKey());
    // if (key.isEncryptionKey())
    // return key;
    // }
    // }
    // return null;
    //
    // }

    //
    // Public main method
    //
    public static void main(final String[] args) {

        Security.addProvider(new BouncyCastleProvider());

        final String TOKEN = "aamine";

        // ----- Encrypt the message to a file them
        final byte[] encdata = SingleSignOnTest.encrypt(TOKEN.getBytes());
        SingleSignOnTest.logger.info("Encrypted: {}", encdata);

        // Encode the byte array to a string
        final byte[] temp = Base64.encode(encdata);
        SingleSignOnTest.logger.info("Temp: {}", temp);

        // us
        byte[] newB = null;
        try {
            newB = Base64.decode(temp);
        } catch (final Exception e) {
            SingleSignOnTest.logger.info("Exception: {}", e);
            return;
        }
        SingleSignOnTest.logger.info("byte array {}", Integer.valueOf(newB.length));

        final String result = SingleSignOnTest.decrypt(newB);
        SingleSignOnTest.logger.info("Decrypted: {}", result);

    }

    //
    // Private class method _decrypt
    //
    private static String _decrypt(final InputStream inputStream, final InputStream keyIn, final char[] passwd)
            throws Exception {

        final InputStream in = PGPUtil.getDecoderStream(inputStream);
        try {
            final PGPObjectFactory pgpF = new BcPGPObjectFactory(in);
            PGPEncryptedDataList enc;
            final Object o = pgpF.nextObject();

            //
            // the first object might be a PGP marker packet.
            //
            if (o instanceof PGPEncryptedDataList) {
                enc = (PGPEncryptedDataList) o;
            } else {
                enc = (PGPEncryptedDataList) pgpF.nextObject();
            }

            //
            // find the secret key
            //
            final Iterator<?> it = enc.getEncryptedDataObjects();
            PGPPrivateKey sKey = null;
            PGPPublicKeyEncryptedData pbe = null;
            while (sKey == null && it.hasNext()) {
                pbe = (PGPPublicKeyEncryptedData) it.next();
                SingleSignOnTest.logger.info("pbe id = {}", Long.valueOf(pbe.getKeyID()));
                sKey = SingleSignOnTest.findSecretKey(keyIn, pbe.getKeyID(), passwd);
            }
            if (sKey == null) {
                in.close();
                throw new IllegalArgumentException("secret key for message not found.");
            }
            final BcPublicKeyDataDecryptorFactory factory = new BcPublicKeyDataDecryptorFactory(sKey);
            final InputStream clear = pbe != null ? pbe.getDataStream(factory) : null;
            final PGPObjectFactory plainFact = new BcPGPObjectFactory(clear);
            Object message = plainFact.nextObject();
            if (message instanceof PGPCompressedData) {
                final PGPCompressedData cData = (PGPCompressedData) message;
                final PGPObjectFactory pgpFact = new BcPGPObjectFactory(cData.getDataStream());
                message = pgpFact.nextObject();
            }
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (message instanceof PGPLiteralData) {
                final PGPLiteralData ld = (PGPLiteralData) message;
                final InputStream unc = ld.getInputStream();
                int ch;
                while ((ch = unc.read()) >= 0) {
                    baos.write(ch);
                }
                unc.close();
            } else if (message instanceof PGPOnePassSignatureList) {
                if (clear != null) {
                    clear.close();
                }
                throw new PGPException("encrypted message contains a signed message - not literal data.");
            } else {
                if (clear != null) {
                    clear.close();
                }
                throw new PGPException("message is not a simple encrypted file - type unknown.");
            }
            if (pbe != null && pbe.isIntegrityProtected()) {
                if (!pbe.verify()) {
                    SingleSignOnTest.logger.error("message failed integrity check");
                } else {
                    SingleSignOnTest.logger.error("message integrity check passed");
                }
            } else {
                SingleSignOnTest.logger.error("no message integrity check");
            }
            if (clear != null) {
                clear.close();
            }
            return baos.toString();
        } catch (final PGPException e) {
            System.err.println(e);
            if (e.getUnderlyingException() != null) {
                e.getUnderlyingException().printStackTrace();
            }
        }
        in.close();
        return null;

    }

    //
    // Private class method _encrypt
    //
    private static void _encrypt(final String fileName, final OutputStream outputStream, final PGPPublicKey encKey)
            throws IOException, PGPException {

        final OutputStream out = new DataOutputStream(outputStream);
        final ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        SingleSignOnTest.logger.info("creating comData...");
        // get the data from the original file
        final PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(CompressionAlgorithmTags.ZIP);
        PGPUtil.writeFileToLiteralData(comData.open(bOut), PGPLiteralData.BINARY, Path.of(fileName).toFile());
        comData.close();
        SingleSignOnTest.logger.info("comData created...");
        SingleSignOnTest.logger.info("using PGPEncryptedDataGenerator...");
        // object that encrypts the data
        final BcPGPDataEncryptorBuilder builder = new BcPGPDataEncryptorBuilder(SymmetricKeyAlgorithmTags.CAST5);
        final PGPEncryptedDataGenerator cPk = new PGPEncryptedDataGenerator(builder);
        final BcPublicKeyKeyEncryptionMethodGenerator generator = new BcPublicKeyKeyEncryptionMethodGenerator(encKey);
        cPk.addMethod(generator);
        SingleSignOnTest.logger.info("used PGPEncryptedDataGenerator...");
        // take the outputstream of the original file and turn it into a byte
        // array
        final byte[] bytes = bOut.toByteArray();
        SingleSignOnTest.logger.info("wrote bOut to byte array...");
        // write the plain text bytes to the armored outputstream
        final OutputStream cOut = cPk.open(out, bytes.length);
        cOut.write(bytes);
        cOut.close();
        cPk.close();
        out.close();
    }

    //
    // Private class method findSecretKey
    //
    private static PGPPrivateKey findSecretKey(final InputStream keyIn, final long keyID, final char[] pass)
            throws IOException, PGPException {

        final PGPSecretKeyRingCollection pgpSec = new BcPGPSecretKeyRingCollection(PGPUtil.getDecoderStream(keyIn));
        final PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);
        if (pgpSecKey == null) {
            return null;
        }
        return pgpSecKey
                .extractPrivateKey(new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(pass));

    }

    //
    // Public class method readFile
    //
    public byte[] readFile(final File file) throws IOException {

        final BufferedInputStream fis = new BufferedInputStream(Files.newInputStream(file.toPath()));
        final byte[] buf = new byte[8192];
        int numRead = 0;
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((numRead = fis.read(buf)) > 0) {
            baos.write(buf, 0, numRead);
        }
        fis.close();
        final byte[] returnVal = baos.toByteArray();
        baos.close();
        return returnVal;

    }

}
