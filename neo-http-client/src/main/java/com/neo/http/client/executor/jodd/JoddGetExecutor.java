package com.neo.http.client.executor.jodd;

import com.alibaba.fastjson.JSONException;
import com.neo.http.client.executor.AbstractGetExecutor;
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
 * @date: 2019-10-11 17:13
 */
public class JoddGetExecutor extends AbstractGetExecutor {
    private static final Logger logger = LoggerFactory.getLogger(JoddGetExecutor.class);

    public JoddGetExecutor(HttpService httpService) {
        super(httpService);
    }

    @Override
    public String execute(String uri, String queryParam) {
        if (queryParam != null) {
            if (uri.indexOf('?') == -1)
                uri += '?';
            uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
        }

        HttpRequest req = HttpRequest.get(uri);
        req.timeout(1000);
        HttpResponse resp = req
                .timeout(httpService.getTimeout())
                .send();
        resp.charset(StringPool.UTF_8);

        String respBody = resp.bodyText();

        try {
            Response response = Response.fromJson(respBody);
            if (response.getCode() != null && !"0".equals(response.getCode())) {
                throw new HttpClientException(new HttpError(
                        response.getRequestId(),
                        response.getCode(),
                        response.getMessage()
                ));
            }
            if (logger.isDebugEnabled()) {
                logger.debug("\n[requestId]: {}\n[url]: {}\n[params]: {}\n[result]: {}", response.getRequestId(), uri, queryParam, response.getResult());
            }
            return response.getResult();
        } catch (JSONException e) {
            return respBody;
        }
    }
}
