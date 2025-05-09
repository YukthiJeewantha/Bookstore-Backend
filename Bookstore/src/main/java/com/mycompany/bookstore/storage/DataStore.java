/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.storage;

import com.mycompany.bookstore.model.Author;
import com.mycompany.bookstore.model.Book;
import com.mycompany.bookstore.model.CartItem;
import com.mycompany.bookstore.model.Customer;
import com.mycompany.bookstore.model.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


public class DataStore {

    private final Map<Long, Book> books = new HashMap<>();
    private final Map<Long, Author> authors = new HashMap<>();
    private final Map<Long, Customer> customers = new HashMap<>();
    private final Map<Long , Order> orders = new HashMap<>();
    private final Map<Long, List<CartItem>> carts = new HashMap<>();
    
    
    private final AtomicLong bookIdGenerator = new AtomicLong(1);
    private final AtomicLong authorIdGenerator = new AtomicLong(1);
    private final AtomicLong customerIdGenerator = new AtomicLong(1);
    private final AtomicLong orderIdGenerator = new AtomicLong(1);
    
    public Book addBook(Book book) {
        if (book.getId() == null) {
            book.setId(bookIdGenerator.getAndIncrement());
        }
        books.put(book.getId(), book);
        return book;
    }
    
    public Author addAuthor(Author author) {
        if (author.getId() == null) {
            author.setId(authorIdGenerator.getAndIncrement());
        }
        authors.put(author.getId(), author);
        return author;
    }
    
    public Customer addCustomer(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(customerIdGenerator.getAndIncrement());
        }
        customers.put(customer.getId(), customer);
        return customer;
    }
    
    public Order addOrder(Order order) {
        if (order.getOrderId() == null) {
            order.setOrderId(orderIdGenerator.getAndIncrement());
        }
        orders.put(order.getOrderId(), order);
        return order;
    }
    
    public Book getBook(Long id) {
        return books.get(id);
    }
    
    public Author getAuthor(Long id) {
        return authors.get(id);
    }
    
    public Customer getCustomer(Long id) {
        return customers.get(id);
    }
    
    public Order getOrder(Long id) {
        return orders.get(id);
    }
    
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
    
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors.values());
    }
    
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }
    
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
    
    public void removeBook(Long id) {
        books.remove(id);
    }
    
    public void removeAuthor(Long id) {
        authors.remove(id);
    }
    
    public void removeCustomer(Long id) {
        customers.remove(id);
    }
    
    public void removeOrder(Long id) {
        orders.remove(id);
    }
    
    public List<CartItem> getCart(Long customerId) {
        return carts.getOrDefault(customerId, new ArrayList<>());
    }
    
    public void updateCart(Long customerId, List<CartItem> cart) {
        carts.put(customerId, cart);
    }
    
    public List<Book> getBooksByAuthor(Long authorId) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthorId().equals(authorId)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }
    
    public List<Order> getCustomerOrders(Long customerId) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getCustomerId().equals(customerId)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }
}
