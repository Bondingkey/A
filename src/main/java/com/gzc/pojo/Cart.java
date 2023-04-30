package com.gzc.pojo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/29  13:30  周六
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class Cart {

    private Map<Integer,CartItem> map = new HashMap<>();
    private Integer totalCount;
    private Double totalAmong;

    public void addCart(Book book){
        CartItem cartItem = map.get(book.getBookId());

        if (cartItem==null){//说明之前没有添加过
            CartItem Item = new CartItem(book,1);//新建一个购物车项
            map.put(book.getBookId(), Item);//添加到集合内
        }else {
            //添加过,cartItem就是那个对象
            cartItem.setCount(cartItem.getCount()+1);
        }

        System.out.println("map = " + map);
    }

    public Integer getTotalCount(){
        Collection<CartItem> values = map.values();
        Integer total = 0;
        for (CartItem value : values) {
            total+=value.getCount();
        }
        this.totalCount=total;
        return totalCount;
    }

    public Double getTotalAmong(){
        Collection<CartItem> values = map.values();
        BigDecimal total = new BigDecimal("0");
        for (CartItem value : values) {
            BigDecimal amount = new BigDecimal(value.getAmount()+"");
            total=total.add(amount);
        }
        this.totalAmong=total.doubleValue();
        return totalAmong;
    }

    public Collection<CartItem> all(){
        return map.values();
    }

    public void deleteCartItem(Integer id){
         map.remove(id);
    }

    public void addCount(Integer id){
        CartItem cartItem = map.get(id);
        cartItem.setCount(cartItem.getCount()+1);
    }

    public void subCount(Integer id){
        CartItem cartItem = map.get(id);
        cartItem.setCount(cartItem.getCount()-1);
    }

    public void changeCount(Integer id,Integer count){
        CartItem cartItem = map.get(id);
        cartItem.setCount(count);
    }
}
