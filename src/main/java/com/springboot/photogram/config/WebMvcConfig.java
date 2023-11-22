package com.springboot.photogram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        //  file:///C:/Users/user/fileForPhotogram/  =>   클라이언트가 보는 경로: http://localhost:8080/upload/파일이름.png
        registry
                .addResourceHandler("/upload/**")  //jsp페이지에서 /upload/** 이런 주소패턴이 나오면 발동한다.
                .addResourceLocations("file:///"+uploadFolder)
                //이 아랫 부분은 복붙해서 사용한다.
                .setCachePeriod(60*10*6)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
