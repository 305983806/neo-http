package com.neo.http.server.filter;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 10:18
 */
public interface SignatureDao {

    String getAccessKeySecret(String accessKeyId);

}
