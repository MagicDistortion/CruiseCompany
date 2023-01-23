package services;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* Класс шифрування паролю користувача */
public class EncodePassword {

    private static final Logger LOGGER = Logger.getLogger(EncodePassword.class);
    /* метод шифрування паролю */
    public String getHashPassword(String input) {
        byte[] byteDigest = new byte[0];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            byteDigest = messageDigest.digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("failed to Encode password", e);
        }
        BigInteger number = new BigInteger(1, byteDigest);
        StringBuilder hashHex = new StringBuilder(number.toString(16));
        while (hashHex.length() < 32) hashHex.insert(0, "0");
        return hashHex.toString();
    }
}