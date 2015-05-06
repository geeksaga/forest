package com.geeksaga.common.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * @author geeksaga
 * @since 0.1
 * @version 0.1
 */
public class KeyGeneratorTest
{
    @Test
    public void testGeneratorKey()
    {
        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());

        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());

        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());

        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());

        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());

        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());

        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());
    }

    @Test
    public void testUniqueKey()
    {
        int length = 10000;
        Set<String> list = new HashSet<>(length);

        for (int i = 0; i < length; i++)
        {
            list.add(KeyGenerator.generateKey());
        }

        assertThat(list.size(), is(length));
    }

    @Test
    public void testUniqueKeyUseSleep() throws InterruptedException
    {
        int length = 100;
        
        Set<String> list = new HashSet<>(length);

        for (int i = 0; i < length; i++)
        {
            list.add(KeyGenerator.generateKey());
            
            Thread.sleep(1);
        }

        assertThat(list.size(), is(length));
    }
}