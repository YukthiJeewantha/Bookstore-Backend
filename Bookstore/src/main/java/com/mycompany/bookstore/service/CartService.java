/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.service;

import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.exception.OutOfStockException;
import com.mycompany.bookstore.model.Book;
import com.mycompany.bookstore.model.CartItem;
import com.mycompany.bookstore.storage.DataStore;
import java.util.ArrayList;
import java.util.List;


public class CartService {
     private final DataStore dataStore;
     
     private final CustomerService cusomerService;
     
     public CartService(DataStore dataStore, CustomerService customerService){
         this.dataStore  = dataStore;
         this.cusomerService = customerService;
         
         
     }
     
     public List<CartItem> getCart(Long customerId){
         cusomerService.getCustomer(customerId);
         return dataStore.getCart(customerId);
     }
     
     public void addToCart(Long customerId, CartItem item){
         validateCartItem(item);
         cusomerService.getCustomer(customerId);
         Book book = dataStore.getBook(item.getBookId());
         
         if(book == null){
             throw new InvalidInputException("Book not found with Id: "+ item.getBookId());
             
         }
         
         List<CartItem> cart = new ArrayList<>(dataStore.getCart(customerId));
         
         boolean found = false;
         
         for(CartItem existing : cart){
             if(existing.getBookId().equals(item.getBookId())){
                 existing.setQuantity(existing.getQuantity()+ item.getQuantity());
                 found = true;
                 break;
             }
         }
         
         if(!found){
             cart.add(item);
         }
         
         dataStore.updateCart(customerId,cart);
     }
     
     public void updateCartItem(Long customerId, Long bookId, CartItem item){
         validateCartItem(item);
         cusomerService.getCustomer(customerId);
         if(!bookId.equals(item.getBookId())){
             throw new InvalidInputException("Cart Item Not Found");
         }
         
         Book book = dataStore.getBook(bookId);
         
         if(book == null){
             throw new InvalidInputException("Book not found with id: "+ bookId);
         }
         
         if(book.getStockQuantity()< item.getQuantity()){
             throw new OutOfStockException("Not enough stock available");
         }
         
         List<CartItem> cart = new ArrayList<>(dataStore.getCart(customerId));
         
         boolean found = false;
         
         for(CartItem existing : cart){
             if(existing.getBookId().equals(bookId)){
                 existing.setQuantity(item.getQuantity());
                 found = true;
                 break;
             }
         }
         
         if(!found){
             throw new InvalidInputException("Book not found in the cart");
         }
         
         dataStore.updateCart(customerId, cart);
         
         
     
     }
     
     
     public void removeFromCart(Long customerId, Long bookId){
         cusomerService.getCustomer(customerId);
         List<CartItem> cart = new ArrayList<>(dataStore.getCart(customerId));
         cart.removeIf(item -> item.getBookId().equals(bookId));
         dataStore.updateCart(customerId,cart);
     }
     
     private void validateCartItem(CartItem item){
         if(item == null){
           throw  new InvalidInputException("Cart item cannt be null");
         }
         if(item.getBookId() == null){
           throw  new InvalidInputException("Book id is required");
         }
         if(item.getQuantity() == null || item.getQuantity() <= 0){
           throw  new InvalidInputException("quantity must be greater than 0");
         }
     }
}
