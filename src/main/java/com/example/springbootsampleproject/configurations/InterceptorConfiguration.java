package com.example.springbootsampleproject.configurations;

import com.example.springbootsampleproject.interceptors.GlobalInterceptor;
import com.example.springbootsampleproject.interceptors.TestInterceptor1;
import com.example.springbootsampleproject.interceptors.TestInterceptor2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TestInterceptor1())
//                .addPathPatterns("/test/test2"); // 해당 interceptor가 특정 URL 에만 작동되게 할 수 있음

        registry.addInterceptor(new GlobalInterceptor())
                .addPathPatterns("/**");
    }
}
