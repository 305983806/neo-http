package com.neo.http.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-16 09:41
 */
public class HttpResponse<T> implements Serializable {

    @JSONField(ordinal = 1)
    private String requestId;

    @JSONField(ordinal = 2)
    private String code;

    @JSONField(ordinal = 3)
    private String message;

    @JSONField(ordinal = 4)
    T result;

    public String toJson() {
        return JSON.toJSONString(this, SerializerFeature.SortField);
    }

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
