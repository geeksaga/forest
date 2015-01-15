package com.geeksaga.common.util;

import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import com.geeksaga.common.enums.DateTimeFormatList;

/**
 * @author geeksaga
 * @since 0.1
 * @version 0.1
 */
public class KeyGenerator
{
    private static final AtomicReference<String> BEFORE_GEN_KEY = new AtomicReference<String>();
    private static final AtomicInteger UNIQUE_KEY = new AtomicInteger();
    private static final String SUFFIX_HIERARCHY = "999";

    private KeyGenerator()
    {}

    /**
     * @return
     */
    public static String generateKey()
    {
        return generateKey("");
    }

    /**
     * @return
     */
    public static Long generateKeyToLong()
    {
        return Long.valueOf(generateKey());
    }

    /**
     * @return
     */
    public static String generateHierarchyKey()
    {
        return generateKey(SUFFIX_HIERARCHY);
    }

    /**
     * @return
     */
    public static Long generateHierarchyKeyToLong()
    {
        return Long.valueOf(generateHierarchyKey());
    }

    /**
     * @param suffix
     * @return
     */
    public static String generateKey(String suffix)
    {
        StringBuilder generateKey = new StringBuilder(calculateDate());
        generateKey.append(suffix);

        long newGenerateKey = Long.valueOf(generateKey.toString());

        generateKey.delete(0, generateKey.length());
        // generateKey.append(String.valueOf(newGenerateKey + UNIQUE_KEY.updateAndGet(i -> i > 8 ? i = 0 : i + 1)));
        generateKey.append(String.valueOf(newGenerateKey + updateAndGet()));

        // FIXME
        // System.out.println(BEFORE_GEN_KEY.get());

        if (BEFORE_GEN_KEY.get() != null)
        {
            newGenerateKey = Long.valueOf(generateKey.toString());

            if (Long.valueOf(BEFORE_GEN_KEY.get()) >= newGenerateKey)
            {
                BEFORE_GEN_KEY.set(generateKey.toString());

                return generateKey(suffix);
            }
        }

        BEFORE_GEN_KEY.set(generateKey.toString());

        return generateKey.toString();
    }

    private static int updateAndGet()
    {
        if(UNIQUE_KEY.get() > 8)
        {
            UNIQUE_KEY.set(1);
        }
        else
        {
            UNIQUE_KEY.incrementAndGet();
        }
        
        return UNIQUE_KEY.get();
    }

    private static String calculateDate()
    {
        long yyyy = Integer.parseInt(DateConvertor.getDateFormat(DateTimeFormatList.YYYY));

        StringBuilder sb = new StringBuilder();
        sb.append(yyyy).append((GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_YEAR) + 100));
        sb.append(DateConvertor.getDateFormat(DateTimeFormatList.HHMMSSSSS));

        return sb.toString();
    }

    private static int poly32 = 0xEDB88320;
    private static int[] table32 = new int[0x100];
    static
    {
        for (int i = 0; i < 0x100; i++)
        {
            int v = i;
            for (int j = 0; j < 8; j++)
            {
                v = ((v & 1) == 1) ? ((v >>> 1) ^ poly32) : (v >>> 1);
            }
            table32[i] = v;
        }
    }

    public static int calc32(String arg)
    {
        int len = arg.length();
        int crc = 0xffffffff;
        for (int z = 0; z < len; z++)
        {
            crc = (crc >>> 8) ^ table32[(crc ^ arg.charAt(z)) & 0xff];
        }
        crc = crc ^ 0xffffffff;
        return crc;
    }

    public static int calc32(byte[] bytes)
    {
        int len = bytes.length;
        int crc = 0xffffffff;
        for (int z = 0; z < len; z++)
        {
            crc = (crc >>> 8) ^ table32[(crc ^ bytes[z]) & 0xff];
        }
        crc = crc ^ 0xffffffff;
        return crc;
    }

    private static long poly64 = 0xd800000000000000L;
    private static long[] table64 = new long[0x100];
    static
    {
        for (int i = 0; i < 0x100; i++)
        {
            long v = i;
            for (int j = 0; j < 8; j++)
            {
                v = ((v & 1) == 1) ? ((v >>> 1) ^ poly64) : (v >>> 1);
            }
            table64[i] = v;
        }
    }

    public static long calc64(byte[] bytes)
    {
        long crc = 0xffffffffffffffffL;
        for (int i = 0; i < bytes.length; i++)
        {
            crc = (crc >>> 8) ^ table64[((int) crc ^ bytes[i]) & 0xff];
        }
        crc = crc ^ 0xffffffffffffffffL;
        return crc;
    }

    public static long calc64(String str)
    {
        int len = str.length();
        long crc = 0xffffffffffffffffL;
        for (int i = 0; i < len; i++)
        {
            crc = (crc >>> 8) ^ table64[((int) crc ^ str.charAt(i)) & 0xff];
        }
        crc = crc ^ 0xffffffffffffffffL;
        return crc;
    }
}