package com.example.demo.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * WebConfig
 * 全局配置类
 * @Configuration 标记这是一个配置类
 * 实现 WebMvcConfigurer 接口以自定义 Spring MVC 配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        // 允许所有来源的跨域请求访问 /api/** 路径下的资源
        registry.addMapping("/api/**")
                // 设置允许前端访问的地址,注意生产环境要改成实际前端的域名
                .allowedOrigins("http://localhost:5173", "http://127.0.0.1:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                // 设置允许的请求头,这里设置为允许所有
                .allowedHeaders("*")
                // 是否允许发送Cookie
                .allowCredentials(true)
                // 预检请求的缓存时间，单位为秒
                .maxAge(3600);
    }
}
