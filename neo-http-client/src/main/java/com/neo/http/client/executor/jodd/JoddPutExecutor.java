package com.neo.http.client.executor.jodd;

import com.alibaba.fastjson.JSONException;
import com.neo.http.client.executor.AbstractPutExecutor;
import com.neo.http.client.httpservice.HttpService;
import com.neo.http.client.lang.HttpClientException;
import com.neo.http.common.bean.HttpError;
import com.neo.http.common.bean.Response;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.util.StringPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-24 09:30
 */
public class JoddPutExecutor extends AbstractPutExecutor {
    private static final Logger logger = LoggerFactory.getLogger(JoddPutExecutor.class);

    public JoddPutExecutor(HttpService httpService) {
        super(httpService);
    }

    @Override
    public String execute(String uri, String putEntity) {
        HttpRequest request = HttpRequest.put(uri);
        request.timeout(super.httpService.getTimeout());
        if (putEntity != null) {
            request.bodyText(putEntity);
        }
        request.contentType(super.httpService.getMeta().getContentType(), StringPool.UTF_8);
        this.setHeader(request);

        HttpResponse response = request.send();
        response.charset(StringPool.UTF_8);
        String responseBody = response.bodyText();
        if (responseBody.isEmpty()) {
            throw new HttpClientException("There is no response body.");
        } else if (responseBody.startsWith("<xml>")) {
            return responseBody;
        } else {
            try {
                Response resp = Response.fromJson(responseBody);
                if (resp.getCode() != null && !"0".equals(resp.getCode())) {
                    throw new HttpClientException(new HttpError(
                            resp.getRequestId(),
                            resp.getCode(),
                            resp.getMessage()
                    ));
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("\n[requestId]: {}\n[url]: {}\n[params]: {}\n[result]: {}", resp.getRequestId(), uri, putEntity, resp.getResult());
                }
                return resp.getResult();
            } catch (JSONException e) {
                return responseBody;
            }
        }
    }

    public void setHeader(HttpRequest request) {
        Map<String, String> map = super.httpService.getMeta().getHeaders();
        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            request.header(entry.getKey(), entry.getValue());
        }
    }
}
