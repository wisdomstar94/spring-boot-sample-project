package com.example.springbootsampleproject.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

/*
    Pageable 을 사용하게 되면 기본적으로 page 가 0부터 시작하게 된다.
    page 를 1부터 시작하게 하고 싶으면 아래와 같은 Configuration 이 필요하다.
*/
@Configuration
public class PageableConfiguration {
    @Bean
    PageableHandlerMethodArgumentResolverCustomizer pageableResolverCustomizer() {
        return pageableResolver -> pageableResolver.setOneIndexedParameters(true);
    }
}
