package edu.gu.hajo.rest.conditional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Used to generated ETag values for HTTP header
 * @author hajo
 */
public class ETagGenerator {

    public static String getMd5Digest(byte[] bytes) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }
        byte[] messageDigest = md.digest(bytes);
        BigInteger number = new BigInteger(1, messageDigest);
        StringBuilder sb = new StringBuilder().append('0');
        sb.append(number.toString(16));
        return sb.toString();
    }

    static String getETagFor(Object  o) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }
        String s = Integer.toString(o.hashCode());
        byte[] messageDigest = md.digest(s.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        StringBuilder sb = new StringBuilder().append('0');
        sb.append(number.toString(16));
        return sb.toString();
    }
}
