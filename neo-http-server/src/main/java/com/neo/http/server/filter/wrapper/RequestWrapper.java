package com.neo.http.server.filter.wrapper;

import jodd.core.JoddCore;
import jodd.io.StreamUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 由于RequestBody是流的形式读取，那么流读了一次就没有了，所以只能被调用一次。为了解决这个问题，我们将流的内容保存下来，从而实现反复读取。
 * 实现方案：
 * 先将RequestBody保存为一个byte数组，然后通过Servlet自带的HttpServletRequestWrapper类覆盖getReader()和getInputStream()方法，使流从保存的byte数组读取。
 *
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 09:33
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = StreamUtil.readBytes(request.getReader(), JoddCore.encoding);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }
}
