package org.example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className CharSwitchUtil
 * @author ryan
 * @createTime 2023-02-08  13:26
 * @description TODO
 * @version 1.0
 */
public class CharSwitchUtil {
    public static void main(String[] args) {
        System.out.println(CharSwitchUtil.unicodeEncode("这是中文"));
    }

    public static String unicodeEncode(String string) {
        char[] utfBytes = string.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    public String unicodeDecode(String string) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }
}
