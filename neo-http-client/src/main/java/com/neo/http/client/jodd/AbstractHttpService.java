package com.neo.http.client.jodd;

import com.neo.http.client.HttpService;
import com.neo.http.client.HttpType;
import com.neo.http.client.RequestExecutor;
import com.neo.http.client.bean.HttpMeta;

/**
 * jodd 的 HttpService 实现类
 *
 * @Author: cp.Chen
 * @since: 0.1.0
 * @date: 2019-10-10 17:01
 */
public abstract class AbstractHttpService implements HttpService {

    private RequestExecutor executor;

    public AbstractHttpService() {
        this.executor = getExecutor();
    }

    @Override
    public String get(String url, String queryParam) {
        return null;
    }

    @Override
    public String post(String url, String postData) {
        return null;
    }

    @Override
    public String post(String url, String postData, HttpMeta meta) {
        return null;
    }

    @Override
    public String put(String url, String putData) {
        return null;
    }

    @Override
    public String put(String url, String putData, HttpMeta meta) {
        return null;
    }

    @Override
    public String delete(String url, String deleteData) {
        return null;
    }

    @Override
    public String delete(String url, String deleteData, HttpMeta meta) {
        return null;
    }

    @Override
    public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) {
        return null;
    }

    public <T, E> T executeInternal (RequestExecutor<T, E> executor, String uri, E data) {
        return null;
    }

    @Override
    public void setEndpoint(String endpoint) {

    }

    @Override
    public String getEndpoint() {
        return null;
    }

    @Override
    public int getTimeout() {
        return 0;
    }

    @Override
    public void setTimeout(int timeout) {

    }

    @Override
    public HttpType getHttpType() {
        return null;
    }

    @Override
    public HttpMeta getMeta() {
        return null;
    }

    protected abstract RequestExecutor getExecutor();
}
