package com.neo.http.common.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-12 11:13
 */
public class HttpResponse<T> implements Serializable {

    // 该条查询的记录id，主要用于排查问题使用
    @JSONField(ordinal = 1)
    private String requestId;

    // 错误码
    @JSONField(ordinal = 2)
    private String code;

    // 错误信息
    @JSONField(ordinal = 3)
    private String message;

    // 实际返回结果，如果沒有返回结果，则为null
    @JSONField(ordinal = 4)
    T result;

    public String toJson() {
        return JSON.toJSONString(this, SerializerFeature.SortField);
    }

    public static HttpResponse fromJson(String json) {
        return JSON.parseObject(json, HttpResponse.class);
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
