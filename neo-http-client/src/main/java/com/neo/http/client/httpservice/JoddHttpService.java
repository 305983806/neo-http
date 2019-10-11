package com.neo.http.client.httpservice;

import com.neo.http.client.bean.HttpType;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-10 17:51
 */
public class JoddHttpService extends AbstractHttpService {

    @Override
    public HttpType getHttpType() {
        return HttpType.JODD_HTTP;
    }
}
