package com.example.springbootsampleproject.interceptors;

import com.example.springbootsampleproject.dtos.RequestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String readBody(final HttpServletRequest request, final HttpServletResponse response) {
        String reqBody = (String) request.getAttribute("requestBody");
        /*
          reqBody 값을 읽어 임의 처리.
        */
        return reqBody;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String uniqueAccessKey = (String) request.getAttribute("accessUniqueKey");

        RequestInfo requestInfo = new RequestInfo(request);
        requestInfo.setBody(this.readBody(request, response));
        this.logger.info("");
        this.logger.info(uniqueAccessKey + " : =========================================================================");
        this.logger.info(uniqueAccessKey + " : [" + requestInfo.getMethod() + "] " + requestInfo.getUri());
        this.logger.info(uniqueAccessKey + " : [request ip] => " + requestInfo.getIp());
        this.logger.info(uniqueAccessKey + " : [request header] => " + requestInfo.getHeader());
        this.logger.info(uniqueAccessKey + " : [request body] => " + requestInfo.getBody());
        this.logger.info(uniqueAccessKey + " : [request query] => " + requestInfo.getQuery());

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
