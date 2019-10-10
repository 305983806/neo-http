package com.neo.http.client;

/**
 * Get请求执行器
 *
 * @Author: cp.Chen
 * @since: 0.1.0
 * @date: 2019-10-10 14:55
 */
public abstract class AbstractGetRequestExecutor implements RequestExecutor<String, String> {

    protected HttpService httpService;

    public AbstractGetRequestExecutor(HttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    public String execute(String uri, String data) {
        switch (httpService.getHttpType()) {
            case JODD_HTTP:

        }
        return null;
    }
}
