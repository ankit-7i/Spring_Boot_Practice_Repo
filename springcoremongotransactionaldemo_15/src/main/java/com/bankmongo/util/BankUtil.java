package com.bankmongo.util;

import java.security.SecureRandom;
import java.util.UUID;

public class BankUtil {

    public static String generateAccountNo() {
        return String.valueOf(
            1000000000000000L + (long)(Math.random() * 9000000000000000L));
    }

    public static String generateUsername(String fname, String dob) {
        return fname + "@" + dob.substring(0,4);
    }

    public static String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom r = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<8;i++)
            sb.append(chars.charAt(r.nextInt(chars.length())));
        return sb.toString();
    }

    public static String generateTxId() {
        return UUID.randomUUID().toString();
    }
}
