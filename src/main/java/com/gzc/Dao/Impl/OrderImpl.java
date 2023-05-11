package com.gzc.Dao.Impl;

import com.gzc.Dao.BaseDaoImpl;
import com.gzc.pojo.Order;

import java.util.List;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/05/01  10:39  周一
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class OrderImpl extends BaseDaoImpl implements OrderDao {
    @Override
    public int addOrder(Order order) {
        String sql ="insert into t_order values(null,?,?,?,?,?,?)";
        int update = this.update(sql, order.getOrderSequence(), order.getCreateTime(), order.getTotalCount(), order.getTotalAmount(), order.getOrderStatus(), order.getUserId());
        return update;
    }

    @Override
    public Integer findIdBySe(String se) {
        String sql = "select order_id from t_order where order_sequence =?";
        return (Integer) this.getValue(sql,se);
    }

    @Override
    public List<Order> findAllOrderBy(Integer id) {
        String sql = "select order_id orderId,order_sequence orderSequence,create_time createTime,total_count totalCount,total_amount totalAmount,order_status orderStatus,user_id userID from t_order where user_id=?";
        return this.getList(Order.class,sql,id);
    }
}
