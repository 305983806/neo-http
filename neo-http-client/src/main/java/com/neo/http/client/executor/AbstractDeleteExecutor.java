package com.neo.http.client.executor;

import com.neo.http.client.httpservice.HttpService;

/**
 * Delete请求执行器
 *
 * @Author: cp.Chen
 * @since: 0.1.0
 * @date: 2019-10-10 14:58
 */
public abstract class AbstractDeleteExecutor implements Executor<String, String> {

    protected HttpService httpService;

    public AbstractDeleteExecutor(HttpService httpService) {
        this.httpService = httpService;
    }

    public static Executor<String, String> create(HttpService httpService) {
        switch (httpService.getHttpType()) {
            case JODD_HTTP:
                return null;
            default:
                throw new IllegalArgumentException(
                        String.format(
                                "The HttpType \"%s\" is not supported. You should chose \"JODD_HTTP\" only when initialization.",
                                httpService.getHttpType()));
        }
    }

}
