package com.gzc.service;

import com.gzc.Dao.Impl.*;
import com.gzc.pojo.*;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/05/01  10:20  周一
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class OrderService {

    private OrderDao orderDao = new OrderImpl();
    private OrderItemDao orderItemDao = new OrderItemImpl();
    private BookDao bookDao = new BookDaoImpl();

    public String toPay(User user, Cart cart){
        //创建前置条件
        String orderid="SGG"+System.currentTimeMillis();
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //创建订单保存数据库
        Order order = new Order(null, orderid, simpleDateFormat, cart.getTotalCount(), cart.getTotalAmong(), 0, user.getId());
        orderDao.addOrder(order);
        //创建订单项保存数据库
        //拿到所有的购物项,一个购物项对应一个订单项
        Collection<CartItem> all = cart.all();
        //获取订单id,根据订单编号订单id
        Integer idBySe = orderDao.findIdBySe(orderid);

        //int a = 10/0;//模拟异常

        for (CartItem cartItem : all) {
            //创建订单项对象
            OrderItem orderItem = new OrderItem(null, cartItem.getBook().getBookName(), cartItem.getBook().getPrice(), cartItem.getBook().getImgPath(), cartItem.getCount(), cartItem.getAmount(), idBySe);
            //将订单项保存到数据库
            orderItemDao.addOrderItem(orderItem);
            //修改销量和库存
            Book book = cartItem.getBook();
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        return orderid;//返回订单号
    }

    public List<Order> findAllOrderBy(Integer id){
        List<Order> allOrderBy = orderDao.findAllOrderBy(id);
        return allOrderBy;
    }
}
