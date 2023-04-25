package com.gzc.Servlet.model;

import com.gzc.Servlet.base.BaseServlet;
import com.gzc.pojo.User;
import com.gzc.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/23  19:55  周日
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserService();


    protected void toRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processTemplate("user/regist",request,response);
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //拿到参数
        Map<String, String[]> parameterMap = request.getParameterMap();

        boolean add = userService.addUser(parameterMap);

        if (add){
            request.setAttribute("registname",request.getParameter("username"));
            this.processTemplate("user/regist_success",request,response);
        }
    }

    protected void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processTemplate("user/login",request,response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.selectUser(username, password);

        if (user==null){
            request.setAttribute("username",username);
            request.setAttribute("password",password);
            request.setAttribute("errorMsg","用户名或密码错误");
            this.processTemplate("user/login",request,response);
        }else {
            request.setAttribute("user",user);
            this.processTemplate("user/login_success",request,response);

        }
    }
}
