package com.andersonkim.newstatsvn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
//WebMvcConfigurerAdapter：只替换指定的配置
//WebMvcConfigurerationSupport：完全接管mvc的配置
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    //配置从域名跳转到指定页面
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/user/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
}
