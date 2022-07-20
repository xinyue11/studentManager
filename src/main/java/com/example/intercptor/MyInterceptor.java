package com.example.intercptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("interceptor");
        // 统一拦截，判断是否有登录（输入有账号密码，userName就会存储到session）
        Object value = request.getSession().getAttribute("username");
        if (value != null) {
            return true;
        }else {
            //response.sendRedirect("/nologin.html");
            request.getRequestDispatcher("/errlogin.html").forward(request,response);
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
