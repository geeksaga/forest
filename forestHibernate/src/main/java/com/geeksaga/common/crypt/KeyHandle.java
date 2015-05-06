package com.geeksaga.common.crypt;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author geeksaga
 * @since 0.1
 */
public class KeyHandle
{
    protected static final Logger logger = LoggerFactory.getLogger(KeyHandle.class);

    private static final String ALGORITHM = "DESede";
    private static SecretKey secretKey;

    protected static SecretKey createKey() throws NoSuchAlgorithmException
    {
        return KeyGenerator.getInstance(ALGORITHM).generateKey();
    }

    /**
     * Save the specified TripleDES SecretKey to the specified file
     */
    protected static void writeKey(SecretKey secretKey, File file) throws Exception
    {
        if (file != null)
        {
            SecretKeyFactory secretKeyfactory = SecretKeyFactory.getInstance(ALGORITHM);
            DESedeKeySpec descedeKeySpec = (DESedeKeySpec) secretKeyfactory.getKeySpec(secretKey, DESedeKeySpec.class);
            byte[] key = descedeKeySpec.getKey();

            FileOutputStream out = null;
            try
            {
                out = new FileOutputStream(file);
                out.write(key);
            }
            catch (Exception e)
            {
                logger.info(e.toString());
                throw e;
            }
            finally
            {
                if (out != null)
                {
                    out.close();
                }
            }
        }
    }

    /**
     * Read a TripleDES secret key from the specified file
     */
    protected static SecretKey readKey(File file) throws Exception
    {

        if (secretKey != null)
        {
            return secretKey;
        }
        else
        {
            DataInputStream in = null;

            try
            {
                in = new DataInputStream(new FileInputStream(file));
                byte[] rawkey = new byte[(int) file.length()];
                in.readFully(rawkey);

                DESedeKeySpec desedeKeyspec = new DESedeKeySpec(rawkey);
                SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
                secretKey = secretKeyFactory.generateSecret(desedeKeyspec);
            }
            catch (Exception e)
            {
                logger.info(e.toString());
                throw e;
            }
            finally
            {
                if (in != null)
                {
                    in.close();
                }
            }

            return secretKey;
        }
    }

    /**
     * Use the specified TripleDES key to encrypt bytes from the input stream and write them to the output stream. This method uses
     * CipherOutputStream to perform the encryption and write bytes at the same time.
     */
    protected static void encrypt(SecretKey secretKey, InputStream in, OutputStream out) throws Exception
    {

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        CipherOutputStream cos = new CipherOutputStream(out, cipher);

        byte[] buffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1)
        {
            cos.write(buffer, 0, bytesRead);
        }

        cos.close();

        // For extra security, don't leave any plaintext hanging around memory.
        Arrays.fill(buffer, (byte) 0);
    }

    /**
     * Use the specified TripleDES key to decrypt bytes ready from the input stream and write them to the output stream. This method uses
     * uses Cipher directly to show how it can be done without CipherInputStream and CipherOutputStream.
     */
    protected static void decrypt(SecretKey secretKey, InputStream in, OutputStream out) throws Exception
    {

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] buffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1)
        {
            out.write(cipher.update(buffer, 0, bytesRead));
        }

        out.write(cipher.doFinal());
        out.flush();
    }

    protected static File loadKeyStore() throws IOException
    {
        return loadKeyStore(null);
    }

    protected static File loadKeyStore(String realPath) throws IOException
    {
        String keyStore = null;

        if (realPath != null)
        {
            keyStore = realPath + File.separator + ".keystore";
        }

        if(keyStore != null)
        {
            File dir = new File(keyStore);
            if (!dir.isDirectory())
            {
                dir.mkdir();
                
                logger.info("KeyStore directory is not found.");
                logger.info("KeyHandle is creating the directory. [" + dir + "]");
            }
            
            File file = new File(keyStore + File.separator + "key.file");
            if (!file.isFile())
            {
                file.createNewFile();
                
                logger.info("KeyStore file is not found.");
                logger.info("KeyHandle is creating the file. [" + file + "]");
            }
            
            return file;
        }
        
        return null;
    }
}