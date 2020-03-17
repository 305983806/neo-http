package com.neo.http.client;

import java.util.Map;

public interface OptionalManager extends HttpManager {

    void setHeaders(Map<String, String> headers);

    void setContentType(String contentType);

}
