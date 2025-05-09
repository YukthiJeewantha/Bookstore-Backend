/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.model;

import java.math.BigDecimal;


public class OrderItem {
    private Long bookId;
    private Integer quantity;
    private  BigDecimal pricePerUnit;

    public OrderItem() {
    }

    public OrderItem(Long bookId, Integer quantity, BigDecimal pricePerUnit) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    
    
}
