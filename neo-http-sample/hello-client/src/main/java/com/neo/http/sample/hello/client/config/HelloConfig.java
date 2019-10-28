package com.neo.http.sample.hello.client.config;

import com.neo.http.sample.hello.api.HelloHttpManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 16:17
 */
@Configuration
public class HelloConfig {
//    @Bean
//    public HelloHttpManager helloFactory() {
//        HelloHttpManager manager = new HelloHttpManager("http://127.0.0.1:8080");
//        return manager;
//    }

    @Bean
    public HelloHttpManager helloHttpManager() {
        return new HelloHttpManager("http://127.0.0.1:8080", "47a536851df1491db155112f84c9acba", "ib0PmiNVFi4VBYAwlYxLyA");
    }
}
