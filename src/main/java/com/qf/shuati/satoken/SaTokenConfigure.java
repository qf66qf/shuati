package com.qf.shuati.satoken;

import cn.dev33.satoken.interceptor.SaInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        //注册sa-Token拦截器,打开注解式鉴权模式
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
    }
}
