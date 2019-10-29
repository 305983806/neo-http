package com.neo.http.client.lang;

import com.neo.http.common.bean.Error;
import com.neo.http.common.lang.NeoHttpException;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 17:20
 */
public class HttpClientException extends NeoHttpException {

//    private Error error;

    public HttpClientException() {
        super();
    }

    public HttpClientException(Error error) {
        super(error);
    }

    public HttpClientException(String message) {
        super(message);
    }

    public HttpClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpClientException(Throwable cause) {
        super(cause);
    }

    protected HttpClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

//    public Error getError() {
//        return error;
//    }
}
