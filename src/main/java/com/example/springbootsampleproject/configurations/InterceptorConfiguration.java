package com.example.springbootsampleproject.configurations;

import com.example.springbootsampleproject.interceptors.GlobalInterceptor;
import com.example.springbootsampleproject.interceptors.TestInterceptor1;
import com.example.springbootsampleproject.interceptors.TestInterceptor2;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer, WebMvcRegistrations {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TestInterceptor1())
//                .addPathPatterns("/test/test2"); // 해당 interceptor가 특정 URL 에만 작동되게 할 수 있음

        registry.addInterceptor(new GlobalInterceptor())
                .addPathPatterns("/**");
    }
}

/*
    아래와 같이 WebMvcConfigurationSupport 을 extends 하게 되면
    Controller 에서 Pageable 로 값을 받지 못하게 되는 현상이 발생한다!!
    그렇기 때문에 위와 extend 에서 implements 로 수정함
*/

//@Configuration
//public class InterceptorConfiguration extends WebMvcConfigurationSupport {
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(new TestInterceptor1())
////                .addPathPatterns("/test/test2"); // 해당 interceptor가 특정 URL 에만 작동되게 할 수 있음
//
//        registry.addInterceptor(new GlobalInterceptor())
//                .addPathPatterns("/**");
//    }
//}
