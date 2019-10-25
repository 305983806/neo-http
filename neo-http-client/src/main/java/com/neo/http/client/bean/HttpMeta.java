package com.neo.http.client.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Http元数据
 *
 * @Author: cp.Chen
 * @since: 0.1.0
 * @date: 2019-10-10 14:33
 */
public class HttpMeta {

    private int timeout;

    private String contentType;

    Map<String, String> headers = new HashMap<>();

    public HttpMeta() {
        this.timeout = 10000;
        this.contentType = ContentType.TEXT_XML;
    }

    public HttpMeta(String contentType) {
        super();
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
