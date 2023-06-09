package org.example.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @description:
 * @author: ryan
 * @date 2023/6/9 16:32
 * @version: 1.0
 */
public class RandomStringUtils {
    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new SecureRandom();

    public static String random(Integer length, String fullSet) {
        char[] chars = new char[length];
        for (int index = 0; index < chars.length; index++) {
            chars[index] = fullSet.charAt(RANDOM.nextInt(fullSet.length()));
        }
        return new String(chars);
    }

    public static String random(Integer length) {
        char[] chars = new char[length];
        for (int index = 0; index < chars.length; index++) {
            chars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(chars);
    }
}
