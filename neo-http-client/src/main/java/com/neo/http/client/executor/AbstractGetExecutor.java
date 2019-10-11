package com.neo.http.client.executor;

import com.neo.http.client.executor.jodd.JoddGetExecutor;
import com.neo.http.client.httpservice.HttpService;

/**
 * Get请求执行器
 *
 * @Author: cp.Chen
 * @since: 0.1.0
 * @date: 2019-10-10 14:55
 */
public abstract class AbstractGetExecutor implements Executor<String, String> {

    protected HttpService httpService;

    public AbstractGetExecutor(HttpService httpService) {
        this.httpService = httpService;
    }

    public static Executor<String, String> create (HttpService httpService) {
        switch (httpService.getHttpType()) {
            case JODD_HTTP:
                return new JoddGetExecutor(httpService);
            default:
                throw new IllegalArgumentException(
                        String.format(
                                "The HttpType \"%s\" is not supported. You should chose \"JODD_HTTP\" only when initialization.",
                                httpService.getHttpType()));
        }
    }
}
