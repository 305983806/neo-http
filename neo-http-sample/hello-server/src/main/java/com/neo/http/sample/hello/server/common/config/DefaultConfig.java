package com.neo.http.sample.hello.server.common.config;

import com.neo.http.server.config.SimpleHttpConfiguration;
import com.neo.http.server.filter.HttpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-15 16:24
 */
//@Configuration
public class DefaultConfig {

    @Bean
    public SimpleHttpConfiguration simpleHttpConfiguration() {
        return new SimpleHttpConfiguration();
    }

}
