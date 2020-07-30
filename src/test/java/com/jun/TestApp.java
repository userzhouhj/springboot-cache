package com.jun;

import com.jun.cache.config.LocalCacheGuavaConfiguration;
import com.jun.cache.config.RedisCacheConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
@SpringBootTest(classes = {LocalCacheGuavaConfiguration.class, RedisCacheConfiguration.class})
@RunWith(SpringRunner.class)
public class TestApp {

    @Test
    public void testApps(){

    }
}
