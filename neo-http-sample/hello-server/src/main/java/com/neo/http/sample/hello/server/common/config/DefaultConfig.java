package com.neo.http.sample.hello.server.common.config;

import com.neo.http.server.filter.SignatureHttpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-15 16:24
 */
@Configuration
public class DefaultConfig {

    @Bean
    public SignatureHttpFilter signatureHttpFilter() {
        return new SignatureHttpFilter("hello");
    }

}
