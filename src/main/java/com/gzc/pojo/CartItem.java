package com.gzc.pojo;

import java.math.BigDecimal;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/29  13:30  周六
 * @Project: BookStore
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
public class CartItem {

    private Book book;//图书信息
    private Integer count;//数量
    private Double amount;//金额

    public CartItem() {
    }

    public CartItem(Book book, Integer count) {
        this.book = book;
        this.count = count;
        BigDecimal count1 = new BigDecimal(this.count+"");
        BigDecimal price = new BigDecimal(this.book.getPrice()+"");
        this.amount=count1.multiply(price).doubleValue();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        BigDecimal count = new BigDecimal(this.count+"");
        BigDecimal price = new BigDecimal(this.book.getPrice()+"");
        this.amount=count.multiply(price).doubleValue();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
        BigDecimal count1 = new BigDecimal(this.count+"");
        BigDecimal price = new BigDecimal(this.book.getPrice()+"");
        this.amount=count1.multiply(price).doubleValue();
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                ", amount=" + amount +
                '}';
    }
}
