package com.gzc.Servlet.model;

import com.gzc.Servlet.base.BaseServlet;
import com.gzc.pojo.User;
import com.gzc.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        this.processTemplate("user/regist", request, response);
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码
        String code = request.getParameter("code");
        Object kaptcha_session_key = request.getSession().getAttribute("KAPTCHA_SESSION_KEY");

        //拿到参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();

        try {
            //将map集合中存的参数分别赋值给user对象的属性(要求:参数的key必须和对象的属性名一致)
            BeanUtils.populate(user,parameterMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (code.equals(kaptcha_session_key)){

            boolean add = userService.addUser(user);

            if (add) {
                request.setAttribute("registname", request.getParameter("username"));
                this.processTemplate("user/regist_success", request, response);
            }

        } else {
            System.out.println("user = " + user);
            request.setAttribute("user",user);
            request.setAttribute("code",code);
            request.setAttribute("codeError","验证码错误");

            this.processTemplate("user/regist",request,response);
        }
    }

    protected void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processTemplate("user/login", request, response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.selectUser(username, password);

        if (user == null) {
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("errorMsg", "用户名或密码错误");
            this.processTemplate("user/login", request, response);
        } else {
            request.getSession().setAttribute("user", user);
            this.processTemplate("user/login_success", request, response);

        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/index.html");
    }
}
