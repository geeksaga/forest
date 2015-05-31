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
import java.nio.file.Files;
import java.nio.file.Paths;
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

        try(InputStream in = Files.newInputStream(Paths.get(path)))
        {
           // Lucense l = yaml.loadAs(in, Lucense.class);

            //System.out.println(l.getPath());
            for (Object data : yaml.loadAll(in))
            {
                System.out.println(((LinkedHashMap<String,String>)data).keySet());
                System.out.println(((LinkedHashMap<String,String>)data).containsKey("lucene"));
                System.out.println(((LinkedHashMap<String,LinkedHashMap<String, String>>)data).get("lucene"));

                //list.add((String) data);
            }
        }
        catch (IOException e)
        {
            Logger.error(e);
        }
    }

    public static String getString()
    {
        return null;
    }

    public class Lucense
    {
        private String path;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}