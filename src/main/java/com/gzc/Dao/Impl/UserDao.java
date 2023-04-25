package com.gzc.Dao.Impl;

import com.gzc.pojo.User;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/21  21:06  周五
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public interface UserDao {

    boolean addUser(User user);

    User selectUser(String username);
}
