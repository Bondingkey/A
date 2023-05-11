package com.gzc.Dao.Impl;

import com.gzc.Dao.BaseDaoImpl;
import com.gzc.Servlet.base.BaseServlet;
import com.gzc.pojo.OrderItem;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/05/01  11:21  周一
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class OrderItemImpl extends BaseDaoImpl implements OrderItemDao {
    @Override
    public int addOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item values(null,?,?,?,?,?,?)";
        return this.update(sql,orderItem.getBookName(),orderItem.getPrice(),orderItem.getImgPath(),orderItem.getItemCount(),orderItem.getItemAmount(),orderItem.getOrderId());
    }
}
