package com.neo.http.client.executor.jodd;

import com.alibaba.fastjson.JSONException;
import com.neo.http.client.executor.AbstractPostExecutor;
import com.neo.http.client.httpservice.HttpService;
import com.neo.http.client.lang.HttpClientException;
import com.neo.http.common.bean.HttpError;
import com.neo.http.common.bean.Response;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-23 17:44
 */
public class JoddPostExecutor extends AbstractPostExecutor {
    private static final Logger logger = LoggerFactory.getLogger(JoddPostExecutor.class);

    public JoddPostExecutor(HttpService httpService) {
        super(httpService);
    }

    @Override
    public String execute(String uri, String postEntity) {
        HttpRequest request = HttpRequest.post(uri);
        request.timeout(super.httpService.getTimeout());
        if (postEntity != null) {
            request.bodyText(postEntity);
        }
        request.contentType(super.httpService.getMeta().getContentType(), "UTF-8");
        this.setHeader(request);

        HttpResponse response = request.send();
        response.charset("UTF-8");
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
                    logger.debug("\n[requestId]: {}\n[url]: {}\n[params]: {}\n[result]: {}", resp.getRequestId(), uri, postEntity, resp.getResult());
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
            Entry<String, String> entry = (Entry<String, String>) it.next();
            request.header(entry.getKey(), entry.getValue());
        }
    }
}
