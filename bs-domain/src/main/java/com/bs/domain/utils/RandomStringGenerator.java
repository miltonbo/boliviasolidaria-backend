package com.bs.domain.utils;

public class RandomStringGenerator {

    private RandomStringGenerator() {}

    public static enum Mode {
        ALPHA, ALPHANUMERIC, ALPHANUMERIC_UPPER, ALPHANUMERIC_LOWER, NUMERIC
    }

    public static String generateRandomString(int length, Mode mode) {

        StringBuilder buffer = new StringBuilder();
        String characters;

        switch(mode){

            case ALPHA:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                break;

            case ALPHANUMERIC:
                characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                break;

            case ALPHANUMERIC_LOWER:
                characters = "abcdefghijklmnopqrstuvwxyz1234567890";
                break;

            case NUMERIC:
                characters = "1234567890";
                break;
                
            case ALPHANUMERIC_UPPER:
                characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                break;

            default:
                characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                break;
        }

        int charactersLength = characters.length();

        for (int i = 0; i < length; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(characters.charAt((int) index));
        }
        return buffer.toString();
    }
}
