package com.gzc.Servlet.model;

import com.google.gson.Gson;
import com.gzc.Servlet.base.BaseServlet;
import com.gzc.pojo.Book;
import com.gzc.pojo.Cart;
import com.gzc.pojo.CartItem;
import com.gzc.service.BookService;
import com.gzc.utils.CommonResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/29  16:26  周六
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
@WebServlet("/cart")
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookService();

    protected void addBookCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart==null){//第一次点击
             cart = new Cart();
             session.setAttribute("cart",cart);
        }

        Book book = bookService.findBook(id);
        cart.addCart(book);

        //获取所有图书的数量
        Integer totalCount = cart.getTotalCount();

        CommonResult ok =CommonResult.ok().setResultData(totalCount);
        String s = new Gson().toJson(ok);
        response.getWriter().print(s);
    }

    protected void ToCartPages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processTemplate("cart/cart",request,response);
    }

    protected void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");

        Collection<CartItem> all = cart.all();
        Integer totalCount = cart.getTotalCount();
        Double totalAmong = cart.getTotalAmong();

        List list = new ArrayList<>();
        list.add(all);
        list.add(totalCount);
        list.add(totalAmong);

        CommonResult commonResult = CommonResult.ok().setResultData(list);
        String s = new Gson().toJson(commonResult);
        System.out.println("s = " + s);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(s);
    }

    protected void clearcart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getSession().removeAttribute("cart");
            this.processTemplate("cart/cart",request,response);
    }

    protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String bookid = request.getParameter("bookid");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.deleteCartItem(Integer.parseInt(bookid));

        //跳转
        showCart(request,response);
    }

    protected void addCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        cart.addCount(Integer.parseInt(id));

        //跳转
        showCart(request,response);
    }

    protected void subBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        cart.subCount(Integer.parseInt(id));

        showCart(request,response);
    }

    protected void changeCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String newCount = request.getParameter("newCount");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        cart.changeCount(Integer.parseInt(id),Integer.parseInt(newCount));

        showCart(request,response);
    }
}
