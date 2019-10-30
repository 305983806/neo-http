package com.neo.http.common.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-12 10:22
 */
public class HttpError implements Error, Serializable {
    @JSONField(ordinal = 1)
    private String requestId;

    @JSONField(ordinal = 2)
    private String code;

    @JSONField(ordinal = 3)
    private String message;

    public HttpError() {}

    public HttpError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public HttpError(String requestId, String code, String message) {
        this.requestId = requestId;
        this.code = code;
        this.message = message;
    }

    public static HttpError fromJson(String json) {
        return JSON.parseObject(json, HttpError.class);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
