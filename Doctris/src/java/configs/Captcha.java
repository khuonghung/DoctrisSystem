/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configs;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Khuong Hung
 */
public class Captcha {
    static char[] SYMBOLS = "?=.*".toCharArray();
    static char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    static char[] NUMBERS = "0123456789".toCharArray();
    static char[] ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789?=.*".toCharArray();
    static Random rand = new SecureRandom();

    public static String getCaptcha(int length) {
        assert length >= 4;
        char[] captcha = new char[length];

        captcha[0] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        captcha[1] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        captcha[2] = NUMBERS[rand.nextInt(NUMBERS.length)];
        captcha[3] = SYMBOLS[rand.nextInt(SYMBOLS.length)];

        for (int i = 4; i < length; i++) {
            captcha[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }

        for (int i = 0; i < captcha.length; i++) {
            int randomPosition = rand.nextInt(captcha.length);
            char temp = captcha[i];
            captcha[i] = captcha[randomPosition];
            captcha[randomPosition] = temp;
        }
        return new String(captcha);
    }

}
