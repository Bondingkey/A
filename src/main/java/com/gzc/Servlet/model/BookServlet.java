package com.gzc.Servlet.model;

import com.gzc.Servlet.base.BaseServlet;
import com.gzc.pojo.Book;
import com.gzc.service.BookService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/25  09:34  周二
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
@WebServlet("/Book")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookService();

    protected void ToBookManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> allBook = bookService.findAllBook();
        request.setAttribute("allBook",allBook);
        this.processTemplate("manager/book_manager",request,response);
    }

    protected void ToBookAddPages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processTemplate("manager/book_add",request,response);
    }

    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Book book = new Book();
        try {
            BeanUtils.populate(book,parameterMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        boolean b = bookService.addBook(book);

        if (b){
            response.sendRedirect(request.getContextPath()+"/Book?flag=ToBookManager");
        }else {
            this.processTemplate("manager/book_add",request,response);
        }

    }

    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookid = request.getParameter("bookid");
        boolean b = bookService.deleteBook(bookid);

        if (b){
            response.sendRedirect(request.getContextPath()+"/Book?flag=ToBookManager");
        }

    }

    protected void ToUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookid = request.getParameter("bookid");
        Book book = bookService.findBook(bookid);
        request.setAttribute("book",book);
        this.processTemplate("manager/book_edit",request,response);
    }

    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Book book = new Book();
        try {
            BeanUtils.populate(book,parameterMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        boolean update = bookService.update(book);

        if (update){
            response.sendRedirect(request.getContextPath()+"/Book?flag=ToBookManager");
        }


    }
}
