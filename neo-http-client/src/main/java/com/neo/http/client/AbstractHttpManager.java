package com.neo.http.client;

import com.neo.http.client.bean.ContentType;
import com.neo.http.client.bean.HttpMeta;
import com.neo.http.client.bean.HttpType;
import com.neo.http.client.executor.factory.Executor;
import com.neo.http.client.executor.factory.Factory;
import com.neo.http.client.executor.jodd.JoddFactory;
import com.neo.http.client.lang.HttpClientException;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-25 11:16
 */
public abstract class AbstractHttpManager implements HttpManager {

    private String endPoint;

    private Factory factory;

    private String accessKeyId;

    private String accessKeySecret;

    private HttpType type = HttpType.JODD_HTTP;

    // 是否开启签名校验
    private boolean isSignature = false;

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
    }

    public AbstractHttpManager(String endPoint) {
        this();
        this.endPoint = endPoint;
    }

    public AbstractHttpManager(String endPoint, String accessKeyId, String accessKeySecret) {
        this(endPoint);
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.isSignature = true;
    }

    @Override
    public String get(String url) {
        Executor<String, String> get = factory.createGet();
        return get.execute(url, null);
    }

    @Override
    public String post(String url, String postData) {
        Executor<String, String> post = factory.createPost();
        post.setMeta(new HttpMeta(ContentType.APPLICATION_JSON));
        return post.execute(url, postData);
    }

    @Override
    public String put(String url, String putData) {
        Executor<String, String> put = factory.createPut();
        put.setMeta(new HttpMeta(ContentType.APPLICATION_JSON));
        return put.execute(url, putData);
    }

    @Override
    public String delete(String url) {
        Executor<String, String> delete = factory.createDelete();
        return delete.execute(url, null);
    }

    protected void setType(HttpType type) {
        this.type = type;
    }

    protected void setSignature(boolean signature) {
        isSignature = signature;
    }
}
