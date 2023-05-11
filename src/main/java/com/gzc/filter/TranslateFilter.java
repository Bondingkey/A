package com.gzc.filter;

import com.gzc.utils.JDBCTools;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/05/01  18:53  周一
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
@WebFilter(urlPatterns = "/*")
public class TranslateFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取数据库链接
        Connection connection = JDBCTools.getConnection();
        try {
            //设置为自动提交
            connection.setAutoCommit(false);
            //放行
            chain.doFilter(request,response);
            //如果没有异常,提交
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            JDBCTools.releaseConnection();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void destroy() {
    }
}
