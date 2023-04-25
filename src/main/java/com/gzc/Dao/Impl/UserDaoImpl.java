package com.gzc.Dao.Impl;

import com.gzc.Dao.BaseDaoImpl;
import com.gzc.pojo.User;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/21  21:07  周五
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao{
    @Override
    public boolean addUser(User user) {
        String sql = "insert into users values(0,?,?,?)";
        int update = this.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        if (update!=0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User selectUser(String username) {
        String sql = "select * from users where username=?";
        return this.getBean(User.class, sql, username);
    }
}
