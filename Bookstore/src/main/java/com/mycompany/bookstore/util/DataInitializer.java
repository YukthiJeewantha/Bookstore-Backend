/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.util;

import com.mycompany.bookstore.model.Author;
import com.mycompany.bookstore.model.Book;
import com.mycompany.bookstore.model.Customer;
import com.mycompany.bookstore.storage.DataStore;
import java.math.BigDecimal;


public class DataInitializer {
    
    
    public static void initializeData(DataStore dataStore){
    // Add authors
        Author author1 = new Author(null, "Martin Wickramasinghe", "Sri Lankan author known for his rural life themes and Sinhala literary classics.");
        Author author2 = new Author(null, "Kumaratunga Munidasa", "Sri Lankan scholar and writer who played various roles, including a teacher, coach, and school inspector.");
        
        dataStore.addAuthor(author1);
        dataStore.addAuthor(author2);
        
        // Add books
        Book book1 = new Book(null, "Madol Doova", author1.getId(), "9789556950076", 1947, 
                new BigDecimal("225.00"), 50);
        Book book2 = new Book(null, "Gamperaliya", author1.getId(), "9789550201365", 1944, 
                new BigDecimal("810.00"), 45);
        Book book3 = new Book(null, "Magul Kama", author2.getId(),"9789552129599", 2015, new BigDecimal("600.00"), 100);
        
        dataStore.addBook(book1);
        dataStore.addBook(book2);
        dataStore.addBook(book3);
        
        // Add customers
        Customer customer1 = new Customer(null, "Ashen Bandara", "ashengmail.com.com", "Ashen123");
        Customer customer2 = new Customer(null, "Iman Fernando", "iman@example.com", "iman1234");
        
        dataStore.addCustomer(customer1);
        dataStore.addCustomer(customer2);
    }
}
