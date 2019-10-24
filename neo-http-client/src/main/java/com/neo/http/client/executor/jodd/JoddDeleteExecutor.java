package com.neo.http.client.executor.jodd;

import com.alibaba.fastjson.JSONException;
import com.neo.http.client.executor.AbstractDeleteExecutor;
import com.neo.http.client.httpservice.HttpService;
import com.neo.http.client.lang.HttpClientException;
import com.neo.http.common.bean.HttpError;
import com.neo.http.common.bean.Response;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.util.StringPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-24 10:25
 */
public class JoddDeleteExecutor extends AbstractDeleteExecutor {
    private static final Logger logger = LoggerFactory.getLogger(JoddDeleteExecutor.class);

    public JoddDeleteExecutor(HttpService httpService) {
        super(httpService);
    }

    @Override
    public String execute(String uri, String queryParam) {
        if (queryParam != null) {
            if (uri.indexOf('?') == -1)
                uri += '?';
            uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
        }

        HttpRequest request = HttpRequest.delete(uri);
        HttpResponse response = request
                .timeout(httpService.getTimeout())
                .send();
        response.charset(StringPool.UTF_8);
        String respBody = response.bodyText();

        try {
            Response resp = Response.fromJson(respBody);
            if (resp.getCode() != null && !"0".equals(resp.getCode())) {
                throw new HttpClientException(new HttpError(
                        resp.getRequestId(),
                        resp.getCode(),
                        resp.getMessage()
                ));
            }
            if (logger.isDebugEnabled()) {
                logger.debug("\n[requestId]: {}\n[url]: {}\n[params]: {}\n[result]: {}", resp.getRequestId(), uri, queryParam, resp.getResult());
            }
            return resp.getResult();
        } catch (JSONException e) {
            return respBody;
        }
    }
}
