package com.huobanplus.sapservice.config;

import com.huobanplus.sapservice.commons.config.DataCenterConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by wuxiongliu on 2016-10-17.
 */

@ActiveProfiles("test")
//@Configuration
@ComponentScan({
        "com.huobanplus.sapservice"
})
@Import({DataCenterConfig.class})
public class TestConfig {
}
