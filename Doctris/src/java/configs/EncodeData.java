/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configs;

import java.util.Base64;

/**
 *
 * @author Khuong Hung
 */
public class EncodeData {

    public static String enCode(String s) {
        String s2 = Base64.getEncoder().encodeToString(s.getBytes());
        return s2;
    }
}
