/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configs;

import static configs.Captcha.CHARS;
import static configs.Captcha.random;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Khuong Hung
 */
public class Password {

    static char[] SYMBOLS = "?=.*".toCharArray();
    static char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    static char[] NUMBERS = "0123456789".toCharArray();
    static char[] ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789?=.*".toCharArray();
    static Random rand = new SecureRandom();

    public static String getPassword(int length) {
        assert length >= 4;
        char[] password = new char[length];

        password[0] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        password[1] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[2] = NUMBERS[rand.nextInt(NUMBERS.length)];
        password[3] = SYMBOLS[rand.nextInt(SYMBOLS.length)];

        for (int i = 4; i < length; i++) {
            password[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }

        for (int i = 0; i < password.length; i++) {
            int randomPosition = rand.nextInt(password.length);
            char temp = password[i];
            password[i] = password[randomPosition];
            password[randomPosition] = temp;
        }
        return new String(password);
    }

}
