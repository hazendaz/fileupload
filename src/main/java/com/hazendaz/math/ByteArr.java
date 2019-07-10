package com.hazendaz.math;

import com.google.common.base.CharMatcher;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteArr {

    public static void main(final String[] args) {
        final ByteArr byteArr = new ByteArr();

        final Byte[] a = { Byte.valueOf((byte) 0x03), Byte.valueOf((byte) 0x00), Byte.valueOf((byte) 0x00),
                Byte.valueOf((byte) 0x00) };
        final Byte[] b = { Byte.valueOf((byte) 0x03), Byte.valueOf((byte) 0x00), Byte.valueOf((byte) 0x00),
                Byte.valueOf((byte) 0x00) };
        final byte[] aa = { (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
        final byte[] bb = { (byte) 0x32, (byte) 0x00, (byte) 0x32, (byte) 0x00 };
        final byte[] cc = { 'f', 'a', 'g', 's', 'g' };

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(a == b);
        System.out.println(Arrays.equals(a, b));

        System.out.println(Arrays.toString(aa));
        System.out.println(Arrays.toString(bb));
        System.out.println(aa == bb);
        System.out.println(Arrays.equals(aa, bb));
        System.out.println(Arrays.toString(cc) + "This doesn't print - liar only some doesn't print");
        byteArr.logger.info("Does this print '{}'?", new String(cc));

        System.out.println("Data types conversion example!");
        final int in = 50;
        System.out.println("Integer: " + in);
        // integer to binary
        final String by = Integer.toBinaryString(in);
        System.out.println("Byte: " + by);
        // integer to hexadecimal
        final String hex = Integer.toHexString(in);
        System.out.println("Hexa decimal: " + hex);
        // integer to octal
        final String oct = Integer.toOctalString(in);
        System.out.println("Octal: " + oct);

        System.out.println(Arrays.toString(cc));
        System.out.println(Arrays.toString(Base64.getEncoder().encode(cc)));

        final long t = 1328175927236L;
        final String epochtime1 = Arrays.toString(Base64.getEncoder().encode(BigInteger.valueOf(t).toByteArray()));
        System.out.println("received base64: " + epochtime1 + " got here\n");

        final byte[] nullToEmpty = new byte[2];
        nullToEmpty[0] = (byte) '\u0000';
        String nullToEmptyString = new String(nullToEmpty, StandardCharsets.ISO_8859_1);
        System.out.println("field is:'" + nullToEmptyString + "'\n");

        nullToEmptyString = CharMatcher.javaIsoControl().trimTrailingFrom(nullToEmptyString);

        System.out.println("field is:'" + nullToEmptyString + "'\n");

    }

    private final Logger logger = LoggerFactory.getLogger(ByteArr.class);

}
