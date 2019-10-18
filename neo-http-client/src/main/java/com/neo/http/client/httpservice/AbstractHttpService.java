package com.neo.http.client.httpservice;

import com.neo.http.client.bean.ClientError;
import com.neo.http.client.bean.ContentType;
import com.neo.http.client.bean.HttpMeta;
import com.neo.http.client.executor.*;
import com.neo.http.client.lang.HttpClientException;
import com.neo.http.common.lang.NeoHttpException;
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
        return this.post(url, putData, meta);
    }

    @Override
    public String put(String url, String putData, HttpMeta meta) {
        this.meta = meta;
        return execute(AbstractPutExecutor.create(this), url, putData);
    }

    @Override
    public String delete(String url, String deleteData) {
        meta = new HttpMeta();
        meta.setContentType(ContentType.APPLICATION_JSON);
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
            } catch (NeoHttpException e) {
                //TODO 对错误码的处理
                ClientError error = e.getError();

                // -1，系统繁忙，1000ms后重试
                if (error.getCode() != null && "-1".equals(error.getCode())) {
                    int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
                    try {
                        logger.warn("系统繁忙，{} s 后重试(第{}次)", sleepMillis/1000, retryTimes+1);
                        Thread.sleep(sleepMillis);
                    } catch (InterruptedException e1) {
                        throw new RuntimeException(e1);
                    }
                } else {
                    throw e;
                }
            }
        } while (retryTimes++ < this.maxRetryTimes);
        //TODO 抛出超出重试次数异常
        throw new NeoHttpException();
    }

    public <T, E> T executeInternal (Executor<T, E> executor, String uri, E data) {
        try {
            T result = executor.execute(uri, data);
            if (logger.isDebugEnabled()) {
                logger.debug("\n[url]: {}\n[params]: {}\n[result]: {}", uri, data, result);
            }
            return result;
        } catch (HttpClientException e) {
            ClientError error = e.getClientError();
            logger.error(String.format("\n[url]: %s\n[params]: %s\n[errmsg]: %s", e.getMessage()), e);
            throw e;
        } catch (HttpException e) {
            //TODO 日志输出
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
        return null;
    }


}
