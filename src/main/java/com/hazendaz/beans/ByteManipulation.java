package com.hazendaz.beans;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

public class ByteManipulation {

    public static void main(final String[] args) throws CharacterCodingException {
        final CharsetDecoder decoder = StandardCharsets.ISO_8859_1.newDecoder();
        final CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();

        // Convert the byte array from starting inputEncoding into UCS2
        final byte[] bufferToConvert = "Hello World! ��".getBytes(StandardCharsets.UTF_8);
        final CharBuffer cbuf = decoder.decode(ByteBuffer.wrap(bufferToConvert));

        // Convert the internal UCS2 representation into outputEncoding
        final ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(cbuf));
        System.out.println(new String(bbuf.array(), 0, bbuf.limit(), StandardCharsets.UTF_8));
    }

}
