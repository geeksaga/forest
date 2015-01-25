package com.geeksaga.forest.util;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.forest.config.DataConfig;

/**
 * @author geeksaga
 * @version 0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = { DataConfig.class })
// @ContextConfiguration(classes = AuditingConfiguration.class)
// , loader = AnnotationConfigWebContextLoader.class)
// @TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@SpringApplicationConfiguration(classes = { DataConfig.class })
public abstract class AbstractRepositoryTestSupport
{}