package com.neo.http.sample.hello.client.common;

import com.neo.http.common.bean.Error;
import com.neo.http.common.lang.NeoHttpException;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-24 17:23
 */
public class HelloException extends NeoHttpException {
    public HelloException() {
        super();
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
