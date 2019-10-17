package com.neo.http.common;

import com.neo.http.common.bean.Error;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-17 11:22
 */
public class BusinessException extends RuntimeException {

    private Error error;

    public BusinessException() {
        super();
    }

    public BusinessException(Error error) {
        this.error = error;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Error getError() {
        return error;
    }
}
