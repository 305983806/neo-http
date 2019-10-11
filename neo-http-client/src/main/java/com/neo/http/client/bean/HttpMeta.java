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

    String contentType;

    Map<String, String> headers = new HashMap<>();

    Map<String, String> queryParams = new HashMap<>();

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

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }
}
