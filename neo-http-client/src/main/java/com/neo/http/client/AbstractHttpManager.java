package com.neo.http.client;

import com.neo.http.client.bean.ContentType;
import com.neo.http.client.bean.HttpMeta;
import com.neo.http.client.bean.HttpType;
import com.neo.http.client.executor.factory.Executor;
import com.neo.http.client.executor.factory.Factory;
import com.neo.http.client.executor.jodd.JoddFactory;
import com.neo.http.client.lang.HttpClientException;

import java.util.Map;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-25 11:16
 */
public abstract class AbstractHttpManager implements HttpManager {

    private Factory factory;

    private HttpType type = HttpType.JODD_HTTP;

    private HttpMeta meta;

    public AbstractHttpManager() {
        switch (type) {
            case JODD_HTTP:
                factory = Factory.getFactory(JoddFactory.class.getName());
                break;
            default:
                throw new HttpClientException(String.format(
                        "The HttpType \"%s\" is not supported. You should chose \"JODD_HTTP\" only when initialization.",
                        type));
        }
        meta = new HttpMeta();
    }

    public AbstractHttpManager(String endPoint) {
        this();
        meta.setEndPoint(endPoint);
    }

    public AbstractHttpManager(String appName, String endPoint) {
        this(endPoint);
        meta.setAppName(appName);
    }

    public AbstractHttpManager(String endPoint, String accessKeyId, String accessKeySecret) {
        this(endPoint);
        meta.setAccessKeyId(accessKeyId);
        meta.setAccessKeySecret(accessKeySecret);
        meta.setSignature(true);
    }

    public AbstractHttpManager(String appName, String endPoint, String accessKeyId, String accessKeySecret) {
        this(endPoint, accessKeyId, accessKeySecret);
        meta.setAppName(appName);
    }

    @Override
    public String get(String url) {
        Executor<String, String> get = factory.createGet();
        setContentType(ContentType.TEXT_XML);
        get.setMeta(meta);
        return get.execute(url, null);
    }

    @Override
    public String post(String url, String postData) {
        Executor<String, String> post = factory.createPost();
        if (this.meta.getContentType() == null)
        setContentType(ContentType.APPLICATION_JSON);
        post.setMeta(meta);
        return post.execute(url, postData);
    }

    @Override
    public String put(String url, String putData) {
        Executor<String, String> put = factory.createPut();
        setContentType(ContentType.APPLICATION_JSON);
        put.setMeta(meta);
        return put.execute(url, putData);
    }

    @Override
    public String delete(String url) {
        Executor<String, String> delete = factory.createDelete();
        setContentType(ContentType.TEXT_XML);
        delete.setMeta(meta);
        return delete.execute(url, null);
    }

    @Override
    public void setContentType(String contentType) {
        this.meta.setContentType(contentType);
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
        this.meta.setHeaders(headers);
    }

    @Override
    public String getEndPoint() {
        return this.meta.getEndPoint();
    }

    private void doSetContentType(String contentType) {
        if (this.meta.getContentType() == null || "".equals(this.meta.getContentType())) {
            this.meta.setContentType(contentType);
        }
    }

}
