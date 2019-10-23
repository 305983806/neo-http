package com.neo.http.server.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.neo.http.common.bean.HttpError;
import com.neo.http.common.bean.SystemError;
import com.neo.http.server.Constants;
import com.neo.http.server.HttpResponse;
import com.neo.http.server.filter.wrapper.ResponseWrapper;
import com.neo.http.server.utils.ThreadMDCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-17 14:16
 */
@WebFilter(filterName = "httpFilter", urlPatterns = "/*")
public class HttpFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(HttpFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("Initializing Filter:[{}]", this.getClass().getSimpleName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ResponseWrapper resp = new ResponseWrapper((HttpServletResponse) response);

        if (request instanceof HttpServletRequest) {
            ThreadMDCUtil.setRequestIdIfAbsent();
        }
        resp.setHeader(Constants.HTTP_HEADER_REQUEST_ID, ThreadMDCUtil.getRequestId());

        chain.doFilter(request, resp);

        String body = this.getStandardResponse(resp);
        resp.setContentLengthLong(body.getBytes().length);
        ServletOutputStream out = response.getOutputStream();
        out.write(body.getBytes());
        out.flush();
        out.close();
    }

    protected String getStandardResponse(ResponseWrapper response) throws IOException {
        String body = new String(response.getContent(), "UTF-8");
        String code, message;
        Object result = null;

        try {
            HttpError error = HttpError.fromJson(body);
            if (error.getCode() == null) {
                code = SystemError.SYS_OK.getCode();
                message = SystemError.SYS_OK.getMessage();
                result = JSON.parseObject(body);
            } else {
                code = error.getCode();
                message = error.getMessage();
            }
        } catch (JSONException e) {
            // 非 JSON 结构
            return body;
        }

        HttpResponse newResp = new HttpResponse();
        newResp.setRequestId(ThreadMDCUtil.getRequestId());
        newResp.setCode(code);
        newResp.setMessage(message);
        newResp.setResult(result);
        return newResp.toJson();
    }

}
