package com.highmobility.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by ttiganik on 08/04/16.
 */
public class Bytes {
    final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String hexFromBytes(byte[] bytes) {
        if (bytes == null) return "(null)";
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }

    public static byte[] bytesFromHex(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }

        return data;
    }

    public static byte[] concatBytes(byte[] a, byte[] b) {
        int aLen = a.length;
        int bLen = b.length;
        byte[] c = new byte[aLen + bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    public static byte[] concatBytes(byte[] a, byte b) {
        int aLen = a.length;

        byte[] c = new byte[aLen + 1];
        System.arraycopy(a, 0, c, 0, aLen);
        c[c.length - 1] = b;

        return c;
    }

    public static void setBytes(byte[] inArray, byte[] toBytes, int offset) {
        for (int i = offset; i < offset + toBytes.length; i++) {
            if (i > inArray.length - 1) return;
            inArray[offset + (i - offset)] = toBytes[i - offset];
        }
    }

    /**
     * Does this byte array begin with match array content?
     *
     * @param source
     *          Byte array to examine
     * @param match
     *          Byte array to locate in <code>source</code>
     * @return true If the starting bytes are equal
     */
    public static boolean startsWith(byte[] source, byte[] match) {
        return startsWith(source, 0, match);
    }

    /**
     * Does this byte array begin with match array content?
     *
     * @param source
     *          Byte array to examine
     * @param offset
     *          An offset into the <code>source</code> array
     * @param match
     *          Byte array to locate in <code>source</code>
     * @return true If the starting bytes are equal
     */
    public static boolean startsWith(byte[] source, int offset, byte[] match) {

        if (match.length > (source.length - offset)) {
            return false;
        }

        for (int i = 0; i < match.length; i++) {
            if (source[offset + i] != match[i]) {
                return false;
            }
        }
        return true;
    }

    public static UUID UUIDFromByteArray(byte[] bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        long high = bb.getLong();
        long low = bb.getLong();
        UUID uuid = new UUID(high, low);
        return uuid;
    }

    public static byte[] bytesFromMacString(String mac) {
        String[] macAddressParts = mac.split(":");

        // convert hex string to byte values
        byte[] macAddressBytes = new byte[6];
        for(int i = 0; i < 6; i++){
            Integer hex = Integer.parseInt(macAddressParts[i], 16);
            macAddressBytes[i] = hex.byteValue();
        }

        return macAddressBytes;
    }

    /**
     * Trim the bytes to given length eg remove the elements that are over length
     */
    public static byte[] trimmedBytes(byte[] bytes, int length) {
        if (bytes.length == length) return bytes;

        byte[] trimmedBytes = new byte[length];

        for (int i = 0; i < length; i++) {
            trimmedBytes[i] = bytes[i];
        }

        return trimmedBytes;
    }

    public static void reverse(byte[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }
}
