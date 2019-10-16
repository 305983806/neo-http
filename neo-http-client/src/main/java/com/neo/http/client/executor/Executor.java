package com.neo.http.client.executor;

/**
 * 请求执行器
 *
 * @Author: cp.Chen
 * @since: 0.1.0
 * @date: 2019-10-10 14:46
 */
public interface Executor<T, E> {

    T execute(String uri, E data);

}