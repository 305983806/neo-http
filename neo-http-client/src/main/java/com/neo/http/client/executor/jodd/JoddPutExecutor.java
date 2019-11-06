package com.neo.http.client.executor.jodd;

import com.alibaba.fastjson.JSON;
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
public class JoddPutExecutor extends AbstractExecutor {
    private static final Logger logger = LoggerFactory.getLogger(JoddPutExecutor.class);

    @Override
    protected String executeInternal(String uri, String putEntity) {
        HttpRequest request = HttpRequest.put(uri);
        request.timeout(super.meta.getTimeout());
        if (putEntity != null) {
            request.bodyText(putEntity);
        }
        request.contentType(super.meta.getContentType(), StringPool.UTF_8);
        super.setHeader(request);
        if (super.meta.isSignature()) {
            // 签名
            super.signature(request);
        }
        jodd.http.HttpResponse httpResponse = request.send();
        httpResponse.charset(StringPool.UTF_8);
        String responseBody = httpResponse.bodyText();
        if (responseBody.isEmpty()) {
            throw new HttpClientException("There is no httpResponse body.");
        } else if (responseBody.startsWith("<xml>")) {
            return responseBody;
        } else {
            try {
                HttpResponse resp = HttpResponse.fromJson(responseBody);
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
                            putEntity,
                            resp.getResult() == null ? resp.getMessage() : resp.getResult());
                }
                return JSON.toJSONString(resp.getResult());
            } catch (JSONException e) {
                return responseBody;
            }
        }
    }
}
