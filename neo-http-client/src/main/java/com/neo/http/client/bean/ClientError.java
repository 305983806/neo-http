package com.neo.http.client.bean;

import com.neo.http.common.bean.Error;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 17:15
 */
public interface ClientError extends Error {

    String getRequestId();

}
