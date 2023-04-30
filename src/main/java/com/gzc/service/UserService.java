package com.gzc.service;

import com.gzc.Dao.Impl.UserDao;
import com.gzc.Dao.Impl.UserDaoImpl;
import com.gzc.pojo.User;
import com.gzc.utils.MD5Util;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;


/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/22  18:37  周六
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class UserService {

    private UserDao userDao = new UserDaoImpl();

    public boolean addUser(User user) {

        //加密
        user.setPassword(MD5Util.encode(user.getPassword()));
        //调用持久化层写入数据返回结果
        boolean b = userDao.addUser(user);
        return b;
    }

    public User selectUser(String username, String password) {

        User user = userDao.selectUser(username);

        if (user!= null){
            String s = MD5Util.encode(password);
            if (s.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }

    public User selectUserById(String username){
       return  userDao.selectUser(username);
    }
}
