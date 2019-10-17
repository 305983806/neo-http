package com.neo.http.sample.hello.server.common.config;

import com.neo.http.sample.hello.server.common.LogFilter;
import com.neo.http.server.filter.HttpFilter;
import com.neo.http.server.utils.ThreadMDCUtil;
import com.neo.http.server.RestExceptionHandler;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-15 16:24
 */
@Configuration
public class DefaultConfig {

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor() {
            @Override
            public <T> Future<T> submit(Callable<T> task) {
                return super.submit(ThreadMDCUtil.wrap(task, MDC.getCopyOfContextMap()));
            }

            @Override
            public void execute(Runnable task) {
                super.execute(ThreadMDCUtil.wrap(task, MDC.getCopyOfContextMap()));
            }
        };

        executor.setCorePoolSize(8);
        executor.initialize();
        return executor;
    }

    @Bean
    public RestExceptionHandler restExceptionHandler() {
        return new RestExceptionHandler();
    }

//    @Bean
//    public FilterRegistrationBean registFilter(LogFilter logFilter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(logFilter);
//        registration.addUrlPatterns("/*");
//        registration.setName(LogFilter.class.getSimpleName());
//        return registration;
//    }

    @Bean
    public HttpFilter httpFilter() {
        return new HttpFilter();
    }
}
