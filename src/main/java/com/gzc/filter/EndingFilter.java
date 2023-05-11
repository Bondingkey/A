package com.gzc.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebFilter(filterName = "EndingFilter",urlPatterns = "/*")
public class EndingFilter{



    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        chain.doFilter(request, response);

    }


    public void init(FilterConfig config) throws ServletException {
    }
    public void destroy() {
    }
}