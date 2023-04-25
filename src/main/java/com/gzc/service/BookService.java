package com.gzc.service;

import com.gzc.Dao.Impl.BookDao;
import com.gzc.Dao.Impl.BookDaoImpl;
import com.gzc.pojo.Book;

import java.util.List;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/25  09:42  周二
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class BookService {

    private BookDao bookDao = new BookDaoImpl();

    public List<Book> findAllBook(){
        return bookDao.findAllBook();
    }

    public boolean addBook(Book book){
        return bookDao.insertBook(book);
    }

    public boolean deleteBook(String id){
        return bookDao.deleteBook(id);
    }

    public Book findBook(String bookid){
        return bookDao.findBook(bookid);
    }

    public boolean update(Book book){
        return bookDao.updateBook(book);
    }

}
