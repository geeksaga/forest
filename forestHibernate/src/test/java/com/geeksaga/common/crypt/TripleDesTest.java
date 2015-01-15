package com.geeksaga.common.crypt;

import java.security.Provider;
import java.security.Security;

import org.junit.Test;

public class TripleDesTest
{
    @Test
    public void testDefault() throws Exception
    {
        Provider[] providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++)
        {
            System.out.println(providers[i].toString());
        }

        String data = "abcdefghij1234567890";
        System.out.println("[" + data + "]");

        TripleDes tripleDes = new TripleDes();
        String encrypt = tripleDes.encrypt(data);
        System.out.println(encrypt);
        String decrypt = tripleDes.decrypt(encrypt);
        System.out.println(decrypt);

        tripleDes = new TripleDes();
        String encrypt1 = tripleDes.encryptOnly(data);
        System.out.println(encrypt1);
        String decrypt1 = tripleDes.decryptOnly(encrypt1);
        System.out.println(decrypt1);
    }
}
