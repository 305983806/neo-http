package com.neo.http.sample.hello.server.common;

import com.neo.http.common.lang.NeoHttpException;
import com.neo.http.common.bean.Error;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 15:20
 */
public class HelloException extends NeoHttpException {

    private Error error;

    public HelloException(Error error) {
        super(error);
    }

    public HelloException(String message) {
        super(message);
    }

    public HelloException(String message, Throwable cause) {
        super(message, cause);
    }

    public HelloException(Throwable cause) {
        super(cause);
    }

    protected HelloException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
