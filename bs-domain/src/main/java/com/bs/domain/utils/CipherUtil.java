package com.bs.domain.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author J. Milton Chambi M.
 */
public class CipherUtil {

    private static final Logger log = Logger.getLogger(CipherUtil.class.getName());

    private static final int RADIX_HEX = 16;
    private static final Random random = new Random();
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ1234567890";
    private static final String NUMBERS = "1234567890";


    private CipherUtil() {
    }

    public static String md5(String str) {
        byte byteData[] = str.getBytes();
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byteData = md.digest();
        } catch (NoSuchAlgorithmException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
        return toHex(byteData);
    }

    public static String toHex(byte data[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(Integer.toString((data[i] & 0xff) + 0x100, RADIX_HEX).substring(1));
        }
        return sb.toString();
    }

    public static String sha256(String str) {
        byte byteData[] = str.getBytes();
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            byteData = md.digest();
        } catch (NoSuchAlgorithmException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
        return toHex(byteData);
    }

    public static String sha512(String passwordToHash) {
        String salt = passwordToHash;
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            log.log(Level.SEVERE, "Problemas al generar SHA512", e);
        }
        return generatedPassword;
    }

    public static String hmac(String key, String data) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            return "";
        }
    }
    
    public static String hmacSafeUrl(String key, String data) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return new BigInteger(sha256_HMAC.doFinal(data.getBytes("UTF-8"))).toString(36);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            return "";
        }
    }

    public static String generateToken(int length, boolean onlyNumbers) {
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            if (onlyNumbers)
                token.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
            else
                token.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return token.toString();
    }

}
