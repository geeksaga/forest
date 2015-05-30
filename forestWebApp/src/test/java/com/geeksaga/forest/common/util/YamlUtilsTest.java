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

import org.junit.Test;

import java.io.File;

/**
 * @author geeksaga
 * @version 0.1
 */
public class YamlUtilsTest
{
    @Test
    public void testLoad()
    {
        YamlUtils.setYaml(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "application.yml");
    }
}