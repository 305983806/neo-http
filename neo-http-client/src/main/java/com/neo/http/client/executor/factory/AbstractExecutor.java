package com.neo.http.client.executor.factory;

import com.neo.http.client.bean.HttpMeta;
import com.neo.http.client.lang.HttpClientException;
import com.neo.http.common.utils.HMACSHA1;
import com.neo.http.common.utils.TimeUtil;
import jodd.http.HttpException;
import jodd.http.HttpRequest;
import jodd.util.StringUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.Md5Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-25 10:25
 */
public abstract class AbstractExecutor implements Executor<String, String> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractExecutor.class);

    protected HttpMeta meta = new HttpMeta();

    private int retrySleepMillis = 1000;

    private int maxRetryTimes = 2;

    private String xDate;

    public AbstractExecutor() {
        if (!StringUtil.isEmpty(meta.getAppName())) {
            xDate = "x-" + meta.getAppName().toLowerCase() + "-date";
        } else {
            xDate = "x-date";
        }
    }

    @Override
    public String execute(String uri, String data) {
        int retryTimes = 0;
        do{
            try {
                return executeInternal(uri, data);
            } catch (HttpException e) {
                // 遇到网络异常，1000ms后重试
                int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
                try {
                    if (logger.isWarnEnabled()) {
                        logger.warn("网络异常，{} s 后重试(第{}次)", sleepMillis/1000, retryTimes+1);
                    }
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                logger.error(e.getMessage());
            }
        } while (retryTimes++ < this.maxRetryTimes);
        throw new HttpClientException(String.format("请求失败，已达到最大重试次数（%s），请报告技术人员。", this.maxRetryTimes));
    }

    @Override
    public void setMeta(HttpMeta meta) {
        this.meta = meta;
    }

    public void setHeader(HttpRequest request) {
        Map<String, String> map = this.meta.getHeaders();
        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            request.header(entry.getKey(), entry.getValue());
        }
    }

    protected void signature(HttpRequest request) {
        StringBuilder builder = new StringBuilder(meta.getAppName().toUpperCase() + "\\s" + meta.getAccessKeyId() + ":");
        //HTTP_METHOD
        String httpMethod = request.method();
        builder.append(httpMethod + "\n");

        //CONTENT_MD5
        String body = request.bodyText();
        if (!StringUtil.isEmpty(body)) {
            String contentMD5 = Md5Crypt.md5Crypt(body.getBytes());
            builder.append(contentMD5 + "\n");
        }

        //CONTENT_TYPE
        builder.append(request.contentType() + "\n");

        //DATE
        String gmt = TimeUtil.date2gmt(new Date());
        builder.append(gmt + "\n");

        //CanonicalizedResource
        builder.append(request.url());

        byte[] hmacBytes = HMACSHA1.hmacSHA1Encrypt(meta.getAccessKeySecret(), builder.toString());

        request.header("Authorization", Base64.encodeBase64URLSafeString(hmacBytes));
        request.header(xDate, gmt);
    }

    protected abstract String executeInternal(String uri, String data);
}
