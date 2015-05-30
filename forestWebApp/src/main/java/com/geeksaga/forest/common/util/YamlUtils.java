/*
 * GeekSaga Class Infomation Library v0.0.1
 *
 * http://geeksaga.com/
 *
 * Copyright 2014 GeekSaga Foundation, Inc. and other contributors
 *
 * Released under the MIT license http://geeksaga.com/license
 */
package com.geeksaga.forest.common.util;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author geeksaga
 * @version 0.1
 */
public class YamlUtils
{
    private static Yaml yaml;
    private static List<String> list = new ArrayList<>();

    public static Yaml getYaml()
    {
        return yaml;
    }

    public static void setYaml(String path)
    {
        YamlUtils.yaml = new Yaml();

        InputStream input = null;
        try
        {
            input = new FileInputStream(new File(path));

            for (Object data : yaml.loadAll(input))
            {
                System.out.println(((LinkedHashMap<String,String>)data).keySet());
                //list.add((String) data);
            }
        }
        catch (FileNotFoundException e)
        {
            Logger.error(e);
        }
    }

    public static String getString()
    {
        return null;
    }
}