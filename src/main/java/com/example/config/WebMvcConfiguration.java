package com.example.config;

import com.example.intercptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //一定要加上这个注解，成为Springboot的配置类，不然不会生效
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override   //拦截器配置
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()) //拦截器注册对象
                .addPathPatterns("/students.html","/grads.html","/teachers.html","/lessons.html"); //指定要拦截的请求
              //  .excludePathPatterns("/login.html","/errlogin.html","/loginser"); //排除请求

    }
}

