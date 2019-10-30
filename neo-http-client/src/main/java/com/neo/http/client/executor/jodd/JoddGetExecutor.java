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
 * @date: 2019-10-25 10:35
 */
public class JoddGetExecutor extends AbstractExecutor {
    private static final Logger logger = LoggerFactory.getLogger(JoddGetExecutor.class);

    @Override
    protected String executeInternal(String uri, String queryParam) {
        if (queryParam != null) {
            if (uri.indexOf('?') == -1)
                uri += '?';
            uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
        }

        HttpRequest req = HttpRequest.get(uri);
        req.timeout(super.meta.getTimeout());
        req.contentType(meta.getContentType());
        if (super.meta.isSignature()) {
            super.signature(req);
        }
        jodd.http.HttpResponse resp = req.send();
        resp.charset(StringPool.UTF_8);
        String respBody = resp.bodyText();

        try {
            HttpResponse httpResponse = HttpResponse.fromJson(respBody);
            if (httpResponse.getCode() != null && !"0".equals(httpResponse.getCode())) {
                throw new HttpClientException(new HttpError(
                        httpResponse.getRequestId(),
                        httpResponse.getCode(),
                        httpResponse.getMessage()
                ));
            }
            if (logger.isDebugEnabled()) {
                logger.debug(
                        "\n[requestId]: {}\n[url]: {}\n[params]: {}\n[result]: {}",
                        httpResponse.getRequestId(),
                        uri,
                        queryParam,
                        httpResponse.getResult() == null ? httpResponse.getMessage() : httpResponse.getResult());
            }
            return httpResponse.getResult();
        } catch (JSONException e) {
            return respBody;
        }
    }
}
