package com.neo.http.client;

import com.neo.http.client.bean.HttpMeta;

import java.util.Map;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-25 11:15
 */
public interface HttpManager {

    String get(String url);

    String post(String url, String postData);

    String put(String url, String putData);

    String delete(String url);

    String getEndPoint();

    void setHeaders(Map<String, String> headers);

    void setContentType(String contentType);

}
