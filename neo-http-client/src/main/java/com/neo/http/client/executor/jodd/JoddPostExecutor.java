package com.neo.http.client.executor.jodd;

import com.neo.http.client.executor.AbstractPostExecutor;
import com.neo.http.client.httpservice.HttpService;
import jodd.http.HttpRequest;
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
    public String execute(String uri, String data) {
        HttpRequest request = HttpRequest.post(uri);
        return null;
    }

    public void setHeader(HttpRequest request) {
        Map<String, String> map = this.httpService.getMeta().getHeaders();
        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {
            Entry<String, String> entry = (Entry<String, String>) it.next();
            request.header(entry.getKey(), entry.getValue());
        }
    }
}
