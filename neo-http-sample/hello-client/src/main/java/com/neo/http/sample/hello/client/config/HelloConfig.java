package com.neo.http.sample.hello.client.config;

import com.neo.http.sample.hello.api.HelloFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 16:17
 */
@Configuration
public class HelloConfig {
    @Bean
    public HelloFactory helloFactory() {
        HelloFactory factory = new HelloFactory();
        factory.setEndpoint("127.0.0.1:8080");
        return factory;
    }
}
