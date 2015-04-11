package com.geeksaga.common.crypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordEncoderWrapper
{
    private static PasswordEncoder encoder = new StandardPasswordEncoder();
    
    public static String encode(String rawPassword)
    { 
        return  encoder.encode(rawPassword);
    }
    
    public static boolean matches(String rawPassword, String encodedPassword)
    {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
