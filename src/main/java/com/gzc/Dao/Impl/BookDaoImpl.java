package com.gzc.Dao.Impl;

import com.gzc.Dao.BaseDaoImpl;
import com.gzc.pojo.Book;

import java.util.List;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/25  09:43  周二
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class BookDaoImpl extends BaseDaoImpl implements BookDao {
    @Override
    public List<Book> findAllBook() {
        String sql ="select id bookId,title bookName,author,price,sales,stock,img_path imgPath from books";
        return this.getList(Book.class,sql);
    }

    @Override
    public boolean insertBook(Book book) {
        String sql = "insert into books values(0,?,?,?,?,?,?)";
        int updateRows = this.update(sql, book.getBookName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
        if (updateRows!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteBook(String bookid) {
        String sql="delete from books where id=?";
        int updaterows = this.update(sql, bookid);
        if (updaterows!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Book findBook(String id) {
        String sql = "select id bookId,title bookName,author,price,sales,stock,img_path imgPath from books where id=?";
        return  this.getBean(Book.class,sql,id);
    }

    @Override
    public boolean updateBook(Book book) {
        String sql = "update books set title=?,author=?,price=?,sales=?,stock=? where id = ?";
        return this.update(sql,book.getBookName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getBookId())>0;
    }

}
