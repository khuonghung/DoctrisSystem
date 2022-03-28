/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configs;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author Khuong Hung
 */
public class EncodeData {

    public static String enCode(String s) {
        byte[] data = s.getBytes(StandardCharsets.UTF_8);
        String s2 = Base64.getEncoder().encodeToString(data);
        return s2;
    }

    public static String deCode(String s) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.getDecoder().decode(s);
        String s2 = new String(decodedBytes);
        return s2;
    }
}
