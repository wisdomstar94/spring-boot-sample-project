package com.example.springbootsampleproject.filters;

import com.example.springbootsampleproject.libraries.CommonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class AccessUniqueKeyFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        this.logger.info("AccessUniqueKeyFilter 진입!");
        request.setAttribute("accessUniqueKey", CommonLibrary.getMakeToken(20));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
