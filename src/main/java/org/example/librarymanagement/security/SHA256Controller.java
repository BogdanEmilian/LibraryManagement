package org.example.librarymanagement.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SHA256Controller {

    public static String hash(String data)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();

            for(int i=0; i<hash.length; i++)
            {
                String hexadecimal = Integer.toHexString(0xff & hash[i]);
                hexString.append(hexadecimal);
            }

            return hexString.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}