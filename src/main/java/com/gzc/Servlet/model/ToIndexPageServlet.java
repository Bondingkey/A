package com.gzc.Servlet.model;

import com.gzc.Servlet.base.BaseServlet;
import com.gzc.pojo.Book;
import com.gzc.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/23  19:41  周日
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
@WebServlet("/index.html")
public class ToIndexPageServlet extends BaseServlet {

    BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> allBook = bookService.findAllBook();
        request.setAttribute("allBook",allBook);
        this.processTemplate("index",request,response);
    }
}
