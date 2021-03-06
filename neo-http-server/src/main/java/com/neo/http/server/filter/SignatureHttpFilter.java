package com.neo.http.server.filter;

import com.neo.http.common.bean.HttpError;
import com.neo.http.common.bean.SystemError;
import com.neo.http.common.utils.HMACSHA1;
import com.neo.http.common.utils.MD5Utils;
import com.neo.http.common.utils.TimeUtil;
import com.neo.http.server.Constants;
import com.neo.http.server.filter.wrapper.RequestWrapper;
import com.neo.http.server.filter.wrapper.ResponseWrapper;
import com.neo.http.server.utils.ThreadMDCUtil;
import jodd.util.StringUtil;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 09:27
 */
public class SignatureHttpFilter extends HttpFilter {
    private static final Logger logger = LoggerFactory.getLogger(SignatureHttpFilter.class);

    // 应用名称
    private String appName;

    // 允许与服务器时间相差时间（毫秒）
    private long permitted_diff = 900000;

    // HEADER常量 Authorization
    private final String AUTHORIZATION = "Authorization";

    // HEADER常量 x-{appName}-date
    private String DATE;

    private String accessKeyId;

    private String accessKeySecret;

    private String inputSignature;

    private SignatureDao signatureDao;

    public SignatureHttpFilter(String appName) {
        this.appName = appName.toUpperCase();
        this.DATE = "x-" + appName.toLowerCase() + "-date";
    }

    public SignatureHttpFilter(SignatureDao signatureDao) {
        if (signatureDao == null) {
            logger.error(
                    "An error occured when initializing {}, because the signatureDao can not be null.",
                    this.getClass().getName());
            throw new IllegalArgumentException(String.format(
                    "An error occured when initializing %s, because the signatureDao can not be null.",
                    this.getClass().getName()));
        }
        this.signatureDao = signatureDao;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("Initializing Filter:[{}]", this.getClass().getSimpleName());
    }

    /**
     * 校验 HTTP 头格式是否符合格式要求
     * 1-须在 HTTP 请求中增加 Authorization（授权）的 Head 来包含签名信息，表明这个消息已被授权。格式为：Authorization: antifake AccessKeyId:Signature，
     * 2-校验请求时间和MNS服务器时间相差超过15分钟
     *
     * @param req
     * @return
     */
    protected boolean headerVerify(HttpServletRequest req) {
        String authzStr = req.getHeader(AUTHORIZATION);
        String dateStr = req.getHeader(DATE);

        // Authorization 校验
        if (authzStr == null || "".equals(authzStr)){
            return false;
        }
        Matcher matcher = Pattern.compile("\\s").matcher(authzStr.trim());
        if (!matcher.find()){
            return false;
        }
        String _appName = authzStr.substring(0, matcher.start());
        if (!_appName.equals(appName)){
            return false;
        }
        String[] signs = authzStr.substring(matcher.start() + 1).split(":");
        if (signs.length != 2 || StringUtil.isEmpty(signs[0]) || StringUtil.isEmpty(signs[1])) {
            return false;
        }

        // DATE 校验
        if (dateStr == null || "".equals(dateStr)) return false;
        try {

            Date date = TimeUtil.gmt2date(dateStr);

            long diff = TimeUtil.getDifference(new Date(), date);

            if (0 > diff || diff > permitted_diff) return false;

        } catch (ParseException e) {
            if (logger.isWarnEnabled()) {
                String requestId = String.valueOf(UUID.randomUUID());
                MDC.put("requestId", requestId);
                logger.error("The HTTP header argument \"{}\" parse error, please check the parameters.", DATE, e);
            }
            return false;
        }

        return true;
    }

    private void getAuthorization(HttpServletRequest req) {
        String authzStr = req.getHeader(AUTHORIZATION);
        Matcher matcher = Pattern.compile("\\s").matcher(authzStr.trim());
        matcher.find();
        String[] signs = authzStr.substring(matcher.start() + 1).split(":");
        accessKeyId = signs[0];
        inputSignature = signs[1];
    }

    private String getBodyString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    protected String signature(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();

        // HTTP_METHOD
        String httpMethod = req.getMethod();
        sb.append(httpMethod + "\n");

        // CONTENT-MD5
        String body = getBodyString(req);
        if (!StringUtils.isEmpty(body)) {
            String contentMD5 = Base64.encodeBase64URLSafeString(MD5Utils.digest(body));
            sb.append(contentMD5 + "\n");
        }

        // CONTENT-TYPE
        String contentType = req.getContentType();
        sb.append(contentType + "\n");

        // DATE
        String date = req.getHeader(DATE);
        sb.append(date + "\n");

        // CanonicalizedResource
        String uri = req.getRequestURI();
        Map<String, String[]> paramMap = req.getParameterMap() == null ? new HashMap(): (Map<String, String[]>) req.getParameterMap();
        if (paramMap.size() > 0 && uri.indexOf('?') == -1) {
            uri += '?';
            for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
                uri += uri.endsWith("?") ?
                        entry.getKey() + "=" + entry.getValue()[0] :
                        '&' + entry.getKey() + "=" + entry.getValue()[0];
            }

        }
        sb.append(uri);

//        this.accessKeySecret = signatureDao.getAccessKeySecret(accessKeyId);
        this.accessKeySecret = "ib0PmiNVFi4VBYAwlYxLyA";
        byte[] hmacBytes = HMACSHA1.hmacSHA1Encrypt(accessKeySecret, sb.toString());

        return Base64.encodeBase64URLSafeString(hmacBytes);
    }

    private void doOutput(ServletResponse response, String body) throws IOException {
        response.setContentLengthLong(body.getBytes().length);
        ServletOutputStream out = response.getOutputStream();
        out.write(body.getBytes());
        out.flush();
        out.close();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = new RequestWrapper((HttpServletRequest) request);
        ResponseWrapper resp = new ResponseWrapper((HttpServletResponse) response);

        // RequestId
        if (request instanceof HttpServletRequest) {
            ThreadMDCUtil.setRequestIdIfAbsent();
        }

        // 校验 Header 参数合法性
        if (!headerVerify(req)) {
            // 签名格式不正确
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            String body = new HttpError(
                    ThreadMDCUtil.getRequestId(),
                    SystemError.SYS_INVALID_AUTHORIZATION_HEAD.getCode(),
                    SystemError.SYS_INVALID_AUTHORIZATION_HEAD.getMessage()
            ).toJson();
            doOutput(response, body);
            return;
        }

        // 获得客户端传入的签名信息：accesskeyId + signature
        this.getAuthorization(req);

        resp.setHeader(Constants.HTTP_HEADER_REQUEST_ID, ThreadMDCUtil.getRequestId());

        // 签名比对
        String signature = signature(req);
        if (!inputSignature.equals(signature)){
            // 签名不匹配
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            String body = new HttpError(
                    ThreadMDCUtil.getRequestId(),
                    SystemError.SYS_SIGNATURE_DOSENOT_MATCH.getCode(),
                    SystemError.SYS_SIGNATURE_DOSENOT_MATCH.getMessage()
            ).toJson();
            doOutput(response, body);
        } else {
            chain.doFilter(req, resp);
            String body = super.getStandardResponse(resp);
            doOutput(response, body);
        }
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setPermitted_diff(long permitted_diff) {
        this.permitted_diff = permitted_diff;
    }
}
