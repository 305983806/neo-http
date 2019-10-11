package com.neo.http.client.executor.jodd;

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
        HttpResponse resp = req
                .timeout(httpService.getTimeout())
                .send();
        resp.charset(StringPool.UTF_8);

        String respBody = resp.bodyText();

        return respBody;
    }
}
