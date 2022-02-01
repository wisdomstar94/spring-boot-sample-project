package com.example.springbootsampleproject.configurations;

import com.example.springbootsampleproject.filters.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean<ExceptionHandlerFilter> exceptionHandlerFilter() {
        FilterRegistrationBean<ExceptionHandlerFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ExceptionHandlerFilter());
        // registrationBean.addUrlPatterns("/user/*"); // 특정 URL 에 맵핑되는 경우에만 필터가 적용되게 할수도 있음
        registrationBean.setOrder(0); // 필터의 순서를 지정
        // registrationBean.setName("TestFilter3"); // 필터의 이름을 지정
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CopyBodyFilter> copyBodyFilter() {
        FilterRegistrationBean<CopyBodyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CopyBodyFilter());
        // registrationBean.addUrlPatterns("/user/*"); // 특정 URL 에 맵핑되는 경우에만 필터가 적용되게 할수도 있음
        registrationBean.setOrder(1); // 필터의 순서를 지정
        // registrationBean.setName("TestFilter3"); // 필터의 이름을 지정
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AccessUniqueKeyFilter> accessUniqueKeyFilter() {
        FilterRegistrationBean<AccessUniqueKeyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AccessUniqueKeyFilter());
        // registrationBean.addUrlPatterns("/user/*"); // 특정 URL 에 맵핑되는 경우에만 필터가 적용되게 할수도 있음
        registrationBean.setOrder(2); // 필터의 순서를 지정
        // registrationBean.setName("TestFilter3"); // 필터의 이름을 지정
        return registrationBean;
    }

//    @Bean
//    public FilterRegistrationBean<JwtCheckFilter> jwtCheckFilter() {
//        FilterRegistrationBean<JwtCheckFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new JwtCheckFilter());
//        // registrationBean.addUrlPatterns("/user/*"); // 특정 URL 에 맵핑되는 경우에만 필터가 적용되게 할수도 있음
//        registrationBean.setOrder(3); // 필터의 순서를 지정
//        // registrationBean.setName("TestFilter3"); // 필터의 이름을 지정
//        return registrationBean;
//    }

    @Bean
    public FilterRegistrationBean<TestFilter1> testFilter1() {
        FilterRegistrationBean<TestFilter1> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TestFilter1());
        // registrationBean.addUrlPatterns("/user/*"); // 특정 URL 에 맵핑되는 경우에만 필터가 적용되게 할수도 있음
        registrationBean.setOrder(4); // 필터의 순서를 지정
        // registrationBean.setName("TestFilter1"); // 필터의 이름을 지정
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<TestFilter2> testFilter2() {
        FilterRegistrationBean<TestFilter2> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TestFilter2());
        // registrationBean.addUrlPatterns("/user/*"); // 특정 URL 에 맵핑되는 경우에만 필터가 적용되게 할수도 있음
        registrationBean.setOrder(5); // 필터의 순서를 지정
        // registrationBean.setName("TestFilter2"); // 필터의 이름을 지정
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<TestFilter3> testFilter3() {
        FilterRegistrationBean<TestFilter3> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TestFilter3());
        // registrationBean.addUrlPatterns("/user/*"); // 특정 URL 에 맵핑되는 경우에만 필터가 적용되게 할수도 있음
        registrationBean.setOrder(6); // 필터의 순서를 지정
        // registrationBean.setName("TestFilter3"); // 필터의 이름을 지정
        return registrationBean;
    }
}
