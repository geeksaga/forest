package com.geeksaga.common.crypt;

import java.security.Key;
import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author geeksaga
 * @version 0.1
 */
public class TripleDes
{
    protected static final Logger logger = LoggerFactory.getLogger(TripleDes.class);

    private static final String TDES_CIPHER = "DESede/ECB/PKCS5Padding";
    // private static final String DES_CIPHER = "DES/ECB/PKCS5Padding";
    // Security.addProvider(new com.sun.crypto.provider.SunJCE());
    private static final String DES = "DES";
    private static final String TRIPEDES = "DESede";
    private static final String CHAR_SET_UTF_8 = "UTF-8";
    private static final String CHAR_SET_8859_1 = "8859_1";
    private SecretKey secretKey = null;
    private Cipher cipher = null;
    private Cipher dcipher = null;

    public TripleDes()
    {
        try
        {
            cipher = Cipher.getInstance(TDES_CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, getKey());

            dcipher = Cipher.getInstance(TDES_CIPHER);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public TripleDes(String path)
    {
        try
        {
            secretKey = KeyHandle.readKey(KeyHandle.loadKeyStore());

            cipher = Cipher.getInstance(TDES_CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            dcipher = Cipher.getInstance(TDES_CIPHER);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void generateKey()
    {
        try
        {
            SecretKey key = KeyHandle.createKey();
            KeyHandle.writeKey(key, KeyHandle.loadKeyStore());
        }
        catch (Exception e)
        {
            logger.info(e.toString());
        }
    }

    /**
     * 고정키 정보
     * 
     * @return
     */
    public String key()
    {
        // return "newbiemyt_0324_99";
        return "ab_032409270827_geeksaga2009";
    }

    /**
     * 키값 24바이트인 경우 TripleDES 아니면 DES
     * 
     * @return
     * @throws Exception
     */
    public Key getKey() throws Exception
    {
        return getKey2(key());// (key().length() == 24) ? getKey2(key()) : getKey1(key());
    }

    /**
     * 지정된 비밀키를 가지고 오는 메서드 (DES) require Key Size : 16 bytes
     *
     * @return
     * @exception Exception
     */
    public Key getKey1(String keyValue) throws Exception
    {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        DESKeySpec desKeySpec = new DESKeySpec(keyValue.getBytes());

        return keyFactory.generateSecret(desKeySpec);
    }

    /**
     * 지정된 비밀키를 가지고 오는 메서드 (TripleDES) require Key Size : 24 bytes
     * 
     * @return
     * @throws Exception
     */
    public Key getKey2(String keyValue) throws Exception
    {
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(TRIPEDES);
        DESedeKeySpec desedeKeySpec = new DESedeKeySpec(keyValue.getBytes());

        return secretKeyFactory.generateSecret(desedeKeySpec);
    }

    public String encrypt(String data)
    {
        if (data == null || data.length() == 0)
        {
            return null;
        }

        byte[] b;
        byte[] ct = {};

        try
        {
            b = data.getBytes(CHAR_SET_UTF_8);
            ct = cipher.doFinal(b);
        }
        catch (Exception e)
        {
            logger.info(e.getMessage(), e);
        }

        StringBuffer buf = new StringBuffer(256);

        for (int i = 0; i < ct.length; i++)
        {
            buf.append(byteToString(ct[i]));
        }

        return buf.toString();
    }

    public String encryptOnly(String data) throws Exception
    {
        if (data == null || data.length() == 0)
        {
            throw new Exception("input data is null.");
        }

        byte[] b = data.getBytes(CHAR_SET_8859_1);

        return new String(cipher.doFinal(b), CHAR_SET_8859_1);
    }

    public String decrypt(String data) throws Exception
    {
        return decrypt(data, getKey());
    }

    public String decrypt(String data, Key key) throws Exception
    {
        if (data == null || data.length() == 0)
        {
            throw new Exception("input data is null.");
        }

        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] b = stringToByte(data);

        return new String(cipher.doFinal(b), CHAR_SET_UTF_8);
    }

    public String decryptOnly(String data) throws Exception
    {
        return decryptOnly(data, getKey());
    }

    public String decryptOnly(String data, Key key) throws Exception
    {
        if (data == null || data.length() == 0)
        {
            throw new Exception("input data is null.");
        }

        dcipher.init(Cipher.DECRYPT_MODE, key);

        return new String(cipher.doFinal(data.getBytes(CHAR_SET_8859_1)), CHAR_SET_8859_1);
    }

    protected String byteToString(byte b)
    {
        int n = (b) + 128;
        char[] dc = new char[3];
        String s = String.valueOf(n);
        char[] sc = s.toCharArray();

        if (n < 10)
        {
            dc[0] = '0';
            dc[1] = '0';
            dc[2] = sc[0];
        }
        else if (n < 100)
        {
            dc[0] = '0';
            dc[1] = sc[0];
            dc[2] = sc[1];
        }
        else
        {
            dc[0] = sc[0];
            dc[1] = sc[1];
            dc[2] = sc[2];
        }
        return new String(dc);
    }

    protected byte[] stringToByte(String src)
    {
        char[] sc = src.toCharArray();
        char[] buf = new char[3];
        int size = sc.length / 3;
        byte[] b = new byte[size];
        for (int i = 0; i < size; i++)
        {
            buf[0] = sc[i * 3];
            buf[1] = sc[i * 3 + 1];
            buf[2] = sc[i * 3 + 2];

            int n = Integer.parseInt(new String(buf));
            b[i] = (byte) (n - 128);
        }

        return b;
    }
}