package com.postgresql.ytdemo.securingweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/people").setViewName("people");
        registry.addViewController("/people").setViewName("people");
        registry.addViewController("/people").setViewName("people");
        registry.addViewController("/login").setViewName("login");
    }

}