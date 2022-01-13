/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configs;

import java.util.Random;

/**
 *
 * @author Khuong Hung
 */
public class Capcha {
    public static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static Random random = new Random();

    public static String getCapcha() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 6; i++) { 
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

}
