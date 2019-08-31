package com.shiro.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域
 */
@Component
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
//        response.setHeader("Access-Control-Allow-Origin", "*");//允许跨域访问的域
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");//允许使用的请求方法，以逗号隔开
        response.setHeader("Access-Control-Max-Age", "3600");// 缓存此次请求的秒数
        //允许使用的请求方法，以逗号隔开
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Cache-Control,Pragma,Content-Type,Token");
        response.setHeader("Access-Control-Allow-Credentials","true");//是否允许请求带有验证信息
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
