package com.geeksaga.common.util;

import static org.junit.Assert.assertNotSame;

import org.junit.Test;

/**
 * @author geeksaga
 * @since 0.1
 * @version 0.1
 */
public class KeyGeneratorTest
{
    @Test
    public void generatorKey()
    {
        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());

        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());

        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());
        
        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());
        
        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());
        
        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());
        
        assertNotSame(KeyGenerator.generateKey(), KeyGenerator.generateKey());
    }
}