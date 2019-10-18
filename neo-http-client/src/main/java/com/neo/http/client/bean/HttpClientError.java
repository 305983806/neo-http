package com.neo.http.client.bean;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 17:27
 */
public class HttpClientError implements ClientError {

    private String requestId;
    private String code;
    private String message;

    public HttpClientError(String requestId, String code, String message) {
        this.requestId = requestId;
        this.code = code;
        this.message = message;
    }

    @Override
    public String getRequestId() {
        return requestId;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
