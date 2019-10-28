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

    private String appName;

    private String endPoint;
    private int timeout = 10000;
    private String contentType = ContentType.TEXT_XML;
    Map<String, String> headers = new HashMap<>();

    private boolean isSignature;
    private String accessKeyId;
    private String accessKeySecret;

    public HttpMeta() {}

    public HttpMeta(String contentType) {
        super();
        this.contentType = contentType;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
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

    public boolean isSignature() {
        return isSignature;
    }

    public void setSignature(boolean signature) {
        isSignature = signature;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
