package com.neo.http.server.config;

import com.neo.http.server.filter.SignatureHttpFilter;
import com.neo.http.server.utils.ThreadMDCUtil;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-21 11:28
 */
public class SignatureHttpConfiguration {

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
    public SignatureHttpFilter signatureHttpFilter() {
        return new SignatureHttpFilter();
    }

}
