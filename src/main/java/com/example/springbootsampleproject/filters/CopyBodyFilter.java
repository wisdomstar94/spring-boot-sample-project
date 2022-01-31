package com.example.springbootsampleproject.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CopyBodyFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        this.logger.info("AccessUniqueKeyFilter 진입!");
        ReadableRequestBodyWrapper wrapper = new ReadableRequestBodyWrapper((HttpServletRequest) request);
        wrapper.setAttribute("requestBody", wrapper.getRequestBody());
        chain.doFilter(wrapper, response); // ServletRequest 를 HttpServletRequestWrapper 으로 wrapping 한 객체를 넘겨야 함
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

class ReadableRequestBodyWrapper extends HttpServletRequestWrapper {
    class ServletInputStreamImpl extends ServletInputStream {
        private InputStream inputStream;

        public ServletInputStreamImpl(final InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public boolean isFinished() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isReady() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public int read() throws IOException {
            return this.inputStream.read();
        }

        @Override
        public int read(final byte[] b) throws IOException {
            return this.inputStream.read(b);
        }

        @Override
        public void setReadListener(final ReadListener listener) {
            // TODO Auto-generated method stub
        }
    }

    private byte[] bytes;
    private String requestBody;

    public ReadableRequestBodyWrapper(final HttpServletRequest request) throws IOException {
        super(request);

        InputStream in = super.getInputStream();
        // request의 InputStream의 content를 byte array로 가져오고
//        this.bytes = IOUtils.toByteArray(in);
        this.bytes = in.readAllBytes();
        // 그 데이터는 따로 저장한다
        this.requestBody = new String(this.bytes);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        // InputStream을 반환해야하면 미리 구해둔 byte array 로
        // 새 InputStream을 만들고 이걸로 ServletInputStream을 새로 만들어 반환
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.bytes);
        return new ServletInputStreamImpl(byteArrayInputStream);
    }

    public String getRequestBody() {
        return this.requestBody;
    }
}
