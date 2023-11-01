/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dto.Staff;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author 84859
 */
public class Utils {

    public static String encodeString(String data) throws NoSuchAlgorithmException {
        String encodedString = "";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data.getBytes());
        byte[] digest = md.digest();
        encodedString = DatatypeConverter
                .printHexBinary(digest);
        return encodedString;
    }
    
 
}