package com.neo.http.client.lang;

import com.neo.http.client.bean.ClientError;
import com.neo.http.common.lang.NeoHttpException;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 17:20
 */
public class HttpClientException extends NeoHttpException {

    private ClientError clientError;

    public HttpClientException() {
        super();
    }

    public HttpClientException(ClientError clientError) {
        super(clientError.getMessage());
        this.clientError = clientError;
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

    public ClientError getClientError() {
        return clientError;
    }
}
