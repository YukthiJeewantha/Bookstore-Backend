/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.bookstore.service;

import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.exception.NotFoundException;
import com.mycompany.bookstore.exception.OutOfStockException;
import com.mycompany.bookstore.model.Book;
import com.mycompany.bookstore.model.CartItem;
import com.mycompany.bookstore.model.Order;
import com.mycompany.bookstore.model.OrderItem;
import com.mycompany.bookstore.storage.DataStore;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class OrderService {
    
    
    private final DataStore dataStore;
    private final CustomerService customerService;
    private final CartService cartService;

    public OrderService(DataStore dataStore, CustomerService customerService, CartService cartService) {
        this.dataStore = dataStore;
        this.customerService = customerService;
        this.cartService = cartService;
    }
    
    public List<Order> getCustomerOrders(Long customerId){
        customerService.getCustomer(customerId);
        return dataStore.getCustomerOrders(customerId);
    
    }
    
    public Order getOrder(Long customerId, Long orderId){
        customerService.getCustomer(customerId);
        Order order = dataStore.getOrder(orderId);
        if(order == null || !order.getCustomerId().equals(customerId)){
            throw new NotFoundException("Order not found with id: "+ orderId);
        }
        
        return order;
    }
    
    public Order createOrder(Long customerId){
        customerService.getCustomer(customerId);
        List<CartItem> cart = cartService.getCart(customerId);
        if(cart.isEmpty()){
            throw new InvalidInputException("Cart is empty");
        }
        
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        
        for(CartItem item: cart){
            Book book = dataStore.getBook(item.getBookId());
            if(book == null){
                throw new NotFoundException("Book not found with Id: "+ item.getBookId());
            }
            
            if(book.getStockQuantity() < item.getQuantity()){
                throw new OutOfStockException("Not enough stcok available for the book: "+ book.getTitle());
            }
            
            OrderItem orderItem = new OrderItem(item.getBookId(), item.getQuantity(), book.getPrice());
            orderItems.add(orderItem);
            totalPrice = totalPrice.add(book.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            
            book.setStockQuantity(book.getStockQuantity() - item.getQuantity());
            dataStore.addBook(book);
        
        }
        
        Order order = new Order(null, customerId, orderItems,totalPrice);
        Order saveOrder = dataStore.addOrder(order);
        
        dataStore.updateCart(customerId, new ArrayList<>());
        
        return saveOrder;
        
    }
    
    
}
