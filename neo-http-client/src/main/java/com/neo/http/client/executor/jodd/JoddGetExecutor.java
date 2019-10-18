package com.neo.http.client.executor.jodd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.neo.http.client.bean.HttpClientError;
import com.neo.http.client.bean.Response;
import com.neo.http.client.bean.ClientError;
import com.neo.http.client.executor.AbstractGetExecutor;
import com.neo.http.client.httpservice.HttpService;
import com.neo.http.client.lang.HttpClientException;
import com.neo.http.common.lang.NeoHttpException;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.util.StringPool;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-11 17:13
 */
public class JoddGetExecutor extends AbstractGetExecutor {

    public static void main(String[] args) {
        String json = "{\"status\":\"OK\",\"request_id\":\"150149648719953661651650\",\"result\":{\"searchtime\":0.402225,\"total\":1,\"num\":0,\"viewtotal\":0,\"items\":[{\"fields\":{\"id\":\"10\",\"name\":\"我是一条新<em>文档</em>的标题\",\"phone\":\"18312345678\",\"index_name\":\"app_schema_demo\"},\"property\":{},\"attribute\":{},\"variableValue\":{},\"sortExprValues\":[\"10\",\"10000.1354238242\"]}],\"facet\":[]},\"error\":{\"code\":\"1000\",\"message\":\"Server error.\"}}\n";
        Response response = Response.fromJson(json);
        System.out.println();
    }

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
                throw new HttpClientException(new HttpClientError(
                        response.getRequestId(),
                        response.getCode(),
                        response.getMessage()
                ));
            }
            return JSON.toJSONString(response.getResult());
        } catch (JSONException e) {
            return respBody;
        }
    }
}
