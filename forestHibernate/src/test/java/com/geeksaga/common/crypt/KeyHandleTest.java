package com.geeksaga.common.crypt;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.junit.Test;

public class KeyHandleTest
{
    @Test
    public void testDefault() throws IOException, Exception
    {
        SecretKey secretKey = KeyHandle.createKey();

        KeyHandle.writeKey(secretKey, KeyHandle.loadKeyStore());
    }
}
