package com.geeksaga.forest.util;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.forest.config.DataConfig;

@RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration
// @ContextConfiguration(classes = { RepositoryTestSupportConfig.class })//, loader = AnnotationConfigWebContextLoader.class)
@ContextConfiguration(classes = { DataConfig.class })
// @ContextConfiguration(classes = AuditingConfiguration.class)
// , loader = AnnotationConfigWebContextLoader.class)
// @TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public abstract class AbstractRepositoryTestSupport
{}