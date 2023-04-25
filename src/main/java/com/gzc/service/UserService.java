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

    public boolean addUser(Map<String, String[]> parameterMap) {
        User user = new User();
        try {
            //将map集合中存的参数分别赋值给user对象的属性(要求:参数的key必须和对象的属性名一致)
            BeanUtils.populate(user,parameterMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
}
