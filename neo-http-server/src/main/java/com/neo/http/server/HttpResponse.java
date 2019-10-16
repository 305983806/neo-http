package com.neo.http.server;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-16 09:41
 */
public class HttpResponse<T> {

    private String requestId;

    private String code;

    private String message;

    T result;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
