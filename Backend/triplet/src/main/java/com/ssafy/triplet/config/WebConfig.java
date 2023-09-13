package com.ssafy.triplet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {//CORS처리  현재 모든요청 허용(프론트서버로 변경 필요)
    //     registry.addMapping("/**")
    //             .allowedOriginPatterns("*")
    //             .allowedMethods("*")
    //             .allowedHeaders("*")
    //             .allowCredentials(true)
    //             .maxAge(3600);

    // }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")  // 정적 리소스에 대한 URL 패턴
                .addResourceLocations("classpath:/static/");  // 정적 리소스가 위치한 디렉터리
    }
}
