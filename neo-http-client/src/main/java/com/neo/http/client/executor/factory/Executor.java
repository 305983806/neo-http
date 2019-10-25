package com.neo.http.client.executor.factory;

import com.neo.http.client.bean.HttpMeta;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-25 11:48
 */
public interface Executor<T, E> {

    T execute(String uri, E data);

    void setMeta(HttpMeta meta);

}
