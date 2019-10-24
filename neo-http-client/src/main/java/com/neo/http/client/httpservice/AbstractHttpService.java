package com.neo.http.client.httpservice;

import com.neo.http.client.bean.ContentType;
import com.neo.http.client.bean.HttpMeta;
import com.neo.http.client.executor.*;
import com.neo.http.client.lang.HttpClientException;
import com.neo.http.common.bean.Error;
import jodd.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * jodd 的 HttpService 实现类
 *
 * @Author: cp.Chen
 * @since: 0.1.0
 * @date: 2019-10-10 17:01
 */
public abstract class AbstractHttpService implements HttpService {
    private static final Logger logger = LoggerFactory.getLogger(AbstractHttpService.class);

    // 端點
    private String endpoint;

    // 超时时间，单位：毫秒，黑夜10秒
    private int timeout = 10000;

    private int retrySleepMillis = 1000;

    private int maxRetryTimes = 2;

    private HttpMeta meta;

    @Override
    public String get(String url) {
        return this.get(url, null);
    }

    @Override
    public String get(String url, String queryParam) {
        return execute(AbstractGetExecutor.create(this), url, queryParam);
    }

    @Override
    public String post(String url, String postData) {
        meta = new HttpMeta();
        meta.setContentType(ContentType.APPLICATION_JSON);
        return this.post(url, postData, meta);
    }

    @Override
    public String post(String url, String postData, HttpMeta meta) {
        this.meta = meta;
        return execute(AbstractPostExecutor.create(this), url, postData);
    }

    @Override
    public String put(String url, String putData) {
        meta = new HttpMeta();
        meta.setContentType(ContentType.APPLICATION_JSON);
        return this.put(url, putData, meta);
    }

    @Override
    public String put(String url, String putData, HttpMeta meta) {
        this.meta = meta;
        return execute(AbstractPutExecutor.create(this), url, putData);
    }

    @Override
    public String delete(String url) {
        return this.delete(url, null);
    }

    @Override
    public String delete(String url, String deleteData) {
        meta = new HttpMeta();
        return this.delete(url, deleteData, meta);
    }

    @Override
    public String delete(String url, String deleteData, HttpMeta meta) {
        this.meta = meta;
        return execute(AbstractDeleteExecutor.create(this), url, deleteData);
    }

    @Override
    public <T, E> T execute(Executor<T, E> executor, String uri, E data) {
        int retryTimes = 0;
        do {
            try {
                return this.executeInternal(executor, uri, data);
            } catch (HttpClientException e) {
                //TODO 对错误码的处理
                Error error = e.getError();

                // -1，系统繁忙，1000ms后重试
                if (error.getCode() != null && "-1".equals(error.getCode())) {
                    int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
                    try {
                        if (logger.isWarnEnabled()) {
                            logger.warn("系统繁忙，{} s 后重试(第{}次)", sleepMillis/1000, retryTimes+1);
                        }
                        Thread.sleep(sleepMillis);
                    } catch (InterruptedException e1) {
                        throw new RuntimeException(e1);
                    }
                } else {
                    throw e;
                }
            }
        } while (retryTimes++ < this.maxRetryTimes);
        throw new HttpClientException(String.format("服务器端内部错误，重试已达到最大次数（%s），请报告技术人员。", this.maxRetryTimes));
    }

    public <T, E> T executeInternal (Executor<T, E> executor, String uri, E data) {
        try {
            T result = executor.execute(uri, data);
            return result;
        } catch (HttpClientException e) {
            Error error = e.getError();
            logger.error("\n[requestId]: {}\n[url]: {}\n[params]: {}\n[errmsg]: {}", error.getRequestId(), uri, data, e.getMessage());
            throw e;
        } catch (HttpException e) {
            //TODO 日志输出
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String getEndpoint() {
        return this.endpoint;
    }

    @Override
    public int getTimeout() {
        return this.timeout;
    }

    @Override
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public HttpMeta getMeta() {
        return this.meta;
    }


}
