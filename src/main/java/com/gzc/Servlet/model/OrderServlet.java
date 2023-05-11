package com.gzc.Servlet.model;

import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.gzc.Servlet.base.BaseServlet;
import com.gzc.pojo.Cart;
import com.gzc.pojo.Order;
import com.gzc.pojo.User;
import com.gzc.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderService();

    protected void topay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数,购物车的信息,用户的信息
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        //调用业务层
        String s = orderService.toPay(user, cart);
        //清空购物车
        session.removeAttribute("cart");
        //页面跳转
        request.setAttribute("seid",s);//将订单号保存到请求域
        this.processTemplate("cart/checkout",request,response);
    }

    protected void myorder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        List<Order> allOrderBy = orderService.findAllOrderBy(user.getId());
        request.setAttribute("orders",allOrderBy);
        this.processTemplate("order/order",request,response);
    }
}
