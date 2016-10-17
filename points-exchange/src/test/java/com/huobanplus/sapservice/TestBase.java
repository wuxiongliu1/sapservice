package com.huobanplus.sapservice;

import com.huobanplus.sapservice.commons.config.WebConfig;
import com.huobanplus.sapservice.config.TestConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wuxiongliu on 2016-10-17.
 */

@ActiveProfiles("test")
@ContextConfiguration(classes = {TestConfig.class, WebConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class TestBase {
}
