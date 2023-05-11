package com.gzc.Dao.Impl;

import com.gzc.pojo.Order;

import java.util.List;

public interface OrderDao {

    int addOrder(Order order);

    Integer findIdBySe(String se);

    List<Order> findAllOrderBy(Integer id);
}
