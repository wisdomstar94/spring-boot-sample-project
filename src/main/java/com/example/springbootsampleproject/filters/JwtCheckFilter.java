package com.example.springbootsampleproject.filters;

import com.example.springbootsampleproject.libraries.CommonLibrary;
import com.example.springbootsampleproject.services.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtCheckFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        this.logger.info("JwtCheckFilter 진입!");
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final String uniqueAccessKey = (String) request.getAttribute("accessUniqueKey");
        final String uri = httpServletRequest.getRequestURI();
        if (!"/login".equals(uri)) {
            String authorization = httpServletRequest.getHeader("Authorization");
            this.logger.info(uniqueAccessKey + " : JwtCheckFilter 진입!");
            this.logger.info(uniqueAccessKey + " : authorization = " + authorization);

            JwtService jwtService = new JwtService();
            jwtService.getClaims(authorization);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
