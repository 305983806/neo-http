package com.neo.http.sample.hello.server.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.neo.http.common.bean.DefaultError;
import com.neo.http.sample.hello.server.common.utils.ThreadMDCUtil;
import com.neo.http.server.HttpResponse;
import com.neo.http.server.ResponseWrapper;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-15 10:35
 */
@Configuration
@WebFilter(filterName = "logFilter", urlPatterns = "/*")
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ResponseWrapper resp = new ResponseWrapper((HttpServletResponse) response);

        if (request instanceof HttpServletRequest) {
            ThreadMDCUtil.setRequestIdIfAbsent();
        }

        resp.setHeader(Constants.HTTP_HEADER_REQUEST_ID, ThreadMDCUtil.getRequestId());

        chain.doFilter(request, resp);

        byte[] content = resp.getContent();

        if (content.length > 0) {
            String str = new String(content, "UTF-8");
            ServletOutputStream out = response.getOutputStream();

            DefaultError error = null;
            try {
                error = JSON.parseObject(str, DefaultError.class);
                if (error.getCode() != null) {
                    buildResponse(error);
                }
            } catch (JSONException e) {
                System.out.println("json exception");
            }

            String str1 = buildResponse(str);
            out.write(str1.getBytes());
            out.flush();
            out.close();
        }
    }

    private String buildResponse(DefaultError error) {
        HttpResponse response = new HttpResponse();
        response.setRequestId(ThreadMDCUtil.getRequestId());
        response.setCode(error.getCode());
        response.setMessage(error.getMessage());

    }

    private String buildResponse(String content) {
        HttpResponse response = new HttpResponse();
        response.setRequestId(ThreadMDCUtil.getRequestId());
        response.setCode("0");
        response.setMessage("OK");
        response.setResult(JSON.parseObject(content));
        return JSON.toJSONString(response);
    }
}
