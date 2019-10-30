package com.neo.http.client.executor.jodd;

import com.alibaba.fastjson.JSONException;
import com.neo.http.client.executor.factory.AbstractExecutor;
import com.neo.http.client.lang.HttpClientException;
import com.neo.http.common.bean.HttpError;
import com.neo.http.common.bean.HttpResponse;
import jodd.http.HttpRequest;
import jodd.util.StringPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-25 10:44
 */
public class JoddDeleteExecutor extends AbstractExecutor {
    private static final Logger logger = LoggerFactory.getLogger(JoddDeleteExecutor.class);

    @Override
    protected String executeInternal(String uri, String queryParam) {
        if (queryParam != null) {
            if (uri.indexOf('?') == -1)
                uri += '?';
            uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
        }

        HttpRequest request = HttpRequest.delete(uri);
        request.timeout(super.meta.getTimeout());
        request.contentType(meta.getContentType());
        if (super.meta.isSignature()) {
            // 签名
            super.signature(request);
        }
        jodd.http.HttpResponse httpResponse = request.send();
        httpResponse.charset(StringPool.UTF_8);
        String respBody = httpResponse.bodyText();

        try {
            HttpResponse resp = HttpResponse.fromJson(respBody);
            if (resp.getCode() != null && !"0".equals(resp.getCode())) {
                throw new HttpClientException(new HttpError(
                        resp.getRequestId(),
                        resp.getCode(),
                        resp.getMessage()
                ));
            }
            if (logger.isDebugEnabled()) {
                logger.debug(
                        "\n[requestId]: {}\n[url]: {}\n[params]: {}\n[result]: {}",
                        resp.getRequestId(),
                        uri,
                        queryParam,
                        resp.getResult() == null ? resp.getMessage() : resp.getResult());
            }
            return resp.getResult();
        } catch (JSONException e) {
            return respBody;
        }
    }
}
