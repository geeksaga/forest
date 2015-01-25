package com.geeksaga.forest;

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
@SpringApplicationConfiguration(classes = { DataConfig.class })
@Transactional
public abstract class AbstractTestSupport
{}