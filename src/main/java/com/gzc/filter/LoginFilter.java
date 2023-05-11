package com.gzc.filter;

import com.gzc.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/05/01  16:53  周一
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
@WebFilter(filterName = "LoginFilter",value = "/order")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httprequest = (HttpServletRequest)request;
        HttpServletResponse httpresponse = (HttpServletResponse) response;

        HttpSession session = httprequest.getSession();
        User user = (User) session.getAttribute("user");

        if (user==null){
            //跳转到登录界面
            httpresponse.sendRedirect(httprequest.getContextPath()+"/user?flag=toLogin");
        }else{
            chain.doFilter(request,response);
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void destroy() {
    }
}
