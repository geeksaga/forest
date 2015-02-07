/*
 * GeekSaga Class Infomation Library v0.0.1
 * 
 * http://geeksaga.com/
 * 
 * Copyright 2014 GeekSaga Foundation, Inc. and other contributors
 * 
 * Released under the MIT license http://geeksaga.com/license
 */

/**
 * @author geeksaga
 * @version 0.1
 */
package com.geeksaga.forest.util;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.geeksaga.forest.config.DataConfig;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = { DataConfig.class })
// @ContextConfiguration(classes = AuditingConfiguration.class)
// , loader = AnnotationConfigWebContextLoader.class)
@SpringApplicationConfiguration(classes = { DataConfig.class })
@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractRepositoryTestSupport
{}