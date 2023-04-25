package com.gzc.Dao.Impl;

import com.gzc.pojo.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAllBook();

    boolean insertBook(Book book);

    boolean deleteBook(String bookid);

    Book findBook(String id);

    boolean updateBook(Book book);

}
