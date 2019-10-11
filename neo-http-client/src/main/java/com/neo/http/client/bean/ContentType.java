package com.neo.http.client.bean;

/**
 * 内容类型常量
 *
 * @Author: cp.Chen
 * @since: 0.1.0
 * @date: 2019-10-10 14:34
 */
public interface ContentType {

    String TEXT_XML = "text/xml";

    String APPLICATION_JSON = "application/json";

    String X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

    String MULTIPART_FROM_DATA = "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW";
}
