package com.neo.http.common.lang;

import com.neo.http.common.bean.Error;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-10 18:42
 */
public class NeoHttpException extends RuntimeException {

    private Error error;

    public NeoHttpException() {
        super();
    }

    public NeoHttpException(Error error) {
        this.error = error;
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

    public Error getError() {
        return error;
    }
}
