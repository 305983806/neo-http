package com.neo.http.client.httpservice;

import com.neo.http.client.executor.Executor;
import com.neo.http.client.bean.HttpType;
import com.neo.http.client.bean.HttpMeta;

/**
 * http 服务接口
 *
 * @Author: cp.Chen
 * @since: 0.1.0
 * @date: 2019-10-10 14:18
 */
public interface HttpService {

    /**
     * 针对所有API中的 GET 请求
     * @param url
     * @param queryParam
     * @return
     */
    String get(String url, String queryParam);

    /**
     * 针对所有API中的 POST 请求，默认支持 content-type:application/json
     * @param url
     * @param postData
     * @return
     */
    String post(String url, String postData);

    /**
     * 针对所有API中的 POST 请求，允许设置 content-type
     *
     * @param url
     * @param postData
     * @param meta
     * @return
     */
    String post(String url, String postData, HttpMeta meta);

    /**
     * 针对所有API中的 PUT 请求，默认支持 content-type:application/json
     * @param url
     * @param putData
     * @return
     */
    String put(String url, String putData);

    /**
     * 针对所有API中的 PUT 请求，允许设置 content-type
     *
     * @param url
     * @param putData
     * @param meta
     * @return
     */
    String put(String url, String putData, HttpMeta meta);

    /**
     * 针对所有API中的 DELETE 请求，默认支持 content-type:application/json
     * @param url
     * @param deleteData
     * @return
     */
    String delete(String url, String deleteData);

    /**
     * 针对所有API中的 DELETE 请求，允许设置 content-type
     *
     * @param url
     * @param deleteData
     * @param meta
     * @return
     */
    String delete(String url, String deleteData, HttpMeta meta);

    /**
     * <pre>
     * {@link #get(String, String)} 和{@link #post(String, String)} 方法的封装
     * @param executor
     * @param uri
     * @param data
     * @param <T>
     * @param <E>
     * @return
     * </pre>
     */
    <T, E> T execute(Executor<T, E> executor, String uri, E data);

    /**
     * 用于给调用端设置服务的端点
     * @param endpoint 端点
     */
    void setEndpoint(String endpoint);

    String getEndpoint();

    int getTimeout();

    /**
     * 设置请求等待超时时间
     * @param timeout
     */
    void setTimeout(int timeout);

    HttpType getHttpType();

    HttpMeta getMeta();

}
