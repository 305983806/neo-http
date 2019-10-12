package com.neo.http.client.executor.jodd;

import com.neo.http.client.NeoHttpException;
import com.neo.http.client.bean.Response;
import com.neo.http.client.executor.AbstractGetExecutor;
import com.neo.http.client.httpservice.HttpService;
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

        Response response = Response.fromJson(respBody);
        switch (response.getStatus()) {
            case "OK":
                return response.getResult();
            case "FAIL":
                throw new NeoHttpException(response.getError());
            default:
                throw new NeoHttpException(String.format("Unknown response status %s has bean re", response.getStatus()));
        }
    }
}
