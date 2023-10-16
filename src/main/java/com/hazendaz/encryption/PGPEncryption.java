/*
 * fileUploadResources (https://github.com/hazendaz/fileUploadResources)
 *
 * Copyright 2009-2023 Hazendaz.
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

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;

import javax.inject.Inject;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.bc.BcPGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyKeyEncryptionMethodGenerator;
import org.slf4j.Logger;

public class PGPEncryption {

    @Inject
    private static Logger logger;

    public static void main(final String[] args) {

        // the keyring that holds the public key we're encrypting with
        // String publicKeyFilePath = "C:\\pgp6.5.8\\pubring.pkr";

        // init the security provider
        Security.addProvider(new BouncyCastleProvider());

        try {

            PGPEncryption.logger.info("Creating a temp file...");

            // create a file and write the string to it
            final File outputfile = File.createTempFile("pgp", null);
            final FileWriter writer = new FileWriter(outputfile, StandardCharsets.UTF_8);
            writer.write("the message I want to encrypt".toCharArray());
            writer.close();

            PGPEncryption.logger.info("Temp file created at ");
            PGPEncryption.logger.info(outputfile.getAbsolutePath());
            PGPEncryption.logger.info(
                    "Reading the temp file to make sure that the bits were written\n----------------------------");

            final BufferedReader isr = new BufferedReader(new FileReader(outputfile, StandardCharsets.UTF_8));
            String line = "";
            while ((line = isr.readLine()) != null) {
                System.out.println(line + "\n");
            }

            // read the key
            // FileInputStream in = new FileInputStream(publicKeyFilePath);
            // PGPPublicKey key = readPublicKey(in);
            final char password[] = { 't', 'e', 's', 't', 'm', 'e' };
            final TestKeyGen testKeyGen = new TestKeyGen();
            final PGPPublicKey key = testKeyGen.createPublicKey("someuser@email.com", password).getPublicKey();

            // find out a little about the keys in the public key ring
            PGPEncryption.logger.info("Key Strength = " + key.getBitStrength());
            PGPEncryption.logger.info("Algorithm = " + key.getAlgorithm());

            int count = 0;
            for (final Iterator<?> iterator = key.getUserIDs(); iterator.hasNext();) {
                count++;
                PGPEncryption.logger.info((String) iterator.next());
            }
            System.out.println("Key Count = " + count);
            // create an armored ascii file
            final FileOutputStream out = new FileOutputStream(outputfile.getAbsolutePath() + ".asc");

            // encrypt the file
            PGPEncryption.encryptFile(outputfile.getAbsolutePath(), out, key);
            out.close();

            PGPEncryption.logger.info("Reading the encrypted file\n----------------------------");
            final BufferedReader isr2 = new BufferedReader(
                    new FileReader(new File(outputfile.getAbsolutePath() + ".asc"), StandardCharsets.UTF_8));
            String line2 = "";
            while ((line2 = isr2.readLine()) != null) {
                System.out.println(line2);
            }

            isr.close();
            isr2.close();

        } catch (final PGPException e) {
            PGPEncryption.logger.error(e.toString());
            PGPEncryption.logger.error(e.getUnderlyingException().toString());

        } catch (final Exception e) {
            PGPEncryption.logger.error(e.toString());
        }

    }

    private static void encryptFile(final String fileName, final OutputStream outputStream, final PGPPublicKey encKey)
            throws IOException, PGPException {

        final OutputStream out = new ArmoredOutputStream(outputStream);

        final ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        PGPEncryption.logger.info("creating comData...");

        // get the data from the original file
        final PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(CompressionAlgorithmTags.ZIP);
        PGPUtil.writeFileToLiteralData(comData.open(bOut), PGPLiteralData.BINARY, new File(fileName));
        comData.close();

        PGPEncryption.logger.info("comData created...");

        PGPEncryption.logger.info("using PGPEncryptedDataGenerator...");

        // object that encrypts the data
        final BcPGPDataEncryptorBuilder dataEncryptor = new BcPGPDataEncryptorBuilder(
                SymmetricKeyAlgorithmTags.TRIPLE_DES);
        dataEncryptor.setWithIntegrityPacket(true);
        dataEncryptor.setSecureRandom(new SecureRandom());

        final PGPEncryptedDataGenerator encryptedDataGenerator = new PGPEncryptedDataGenerator(dataEncryptor);
        encryptedDataGenerator.addMethod(new BcPublicKeyKeyEncryptionMethodGenerator(encKey));

        PGPEncryption.logger.info("used PGPEncryptedDataGenerator...");

        // take the outputstream of the original file and turn it into a byte
        // array
        final byte[] bytes = bOut.toByteArray();

        PGPEncryption.logger.info("wrote bOut to byte array...");

        // write the plain text bytes to the armored outputstream
        final OutputStream cOut = encryptedDataGenerator.open(out, bytes.length);
        cOut.write(bytes);

        // cOut.close();
        encryptedDataGenerator.close();
        out.close();

    }

}
