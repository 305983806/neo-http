package com.neo.http.client.jodd;

import com.neo.http.client.RequestExecutor;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.util.StringPool;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-10 18:11
 */
public class JoddGetRequestExecutor implements RequestExecutor<String, String> {
    @Override
    public String execute(String uri, String queryParam) {
        if (queryParam != null) {
            if (uri.indexOf('?') == -1)
                uri += '?';
            uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
        }

        HttpRequest req = HttpRequest.get(uri);
        req.timeout(10000);
        HttpResponse resp = req.send();
        resp.charset(StringPool.UTF_8);

        String respBody = resp.bodyText();

        return respBody;
    }
}
