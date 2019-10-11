package com.neo.http.client;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-10 18:42
 */
public class NeoHttpException extends RuntimeException {

    public NeoHttpException() {
        super();
    }

    public NeoHttpException(String message) {
        super(message);
    }

    public NeoHttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeoHttpException(Throwable cause) {
        super(cause);
    }

    protected NeoHttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
