/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.service;

import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.exception.NotFoundException;
import com.mycompany.bookstore.model.Book;
import com.mycompany.bookstore.storage.DataStore;
import java.util.List;


public class BookService {

    
    private final DataStore dataStore;
    
    public BookService(DataStore dataStore) {
        
        this.dataStore = dataStore;
        
 
    }
    
    public List<Book> getAllBooks(){
        return dataStore.getAllBooks();
    }
    
    public Book getBook(Long id){
        Book book = dataStore.getBook(id);
        
        if(book == null){
            throw new NotFoundException("Book not found with ID: "+ id);
        }
        
        return book;
    }
    
    public Book createBook(Book book){
        validateBook(book);
        
        if(dataStore.getAuthor(book.getAuthorId())== null){
            throw new NotFoundException("Author notfound with ID: "+ book.getAuthorId());
        }
        
        
        return dataStore.addBook(book);
    }
    
    public Book updateBook(Long id, Book book){
        validateBook(book);
        Book exsisting = getBook(id);
        if(dataStore.getAuthor(book.getAuthorId())== null){
            throw new NotFoundException("Author notfound with ID: "+ book.getAuthorId());
        }
        
        book.setId(id);
        return dataStore.addBook(book);
    }
    
    public void deletebook(Long id){
        getBook(id);
        dataStore.removeBook(id);
    }
    
    public List<Book> getBooksByAuthor(Long authorId){
        if(dataStore.getAuthor(authorId)== null){
            throw new NotFoundException("Author not found with Id: "+ authorId);
        }
        
        return dataStore.getBooksByAuthor(authorId);
    }
    
    
    private void validateBook(Book book){
        if(book==null){
            throw new InvalidInputException("Book cannot be null");
        }
        if(book.getTitle()== null || book.getTitle().trim().isEmpty()){
            throw new InvalidInputException("Book title is required");
        }
        if(book.getAuthorId()== null){
            throw new InvalidInputException("Author ID is required");
        }
        if(book.getIsbn() == null || book.getIsbn().trim().isEmpty()){
            throw new InvalidInputException("ISBN is required");
        }
        if(book.getPrice() == null || book.getPrice().compareTo(java.math.BigDecimal.ZERO)< 0){
            throw new InvalidInputException("Valid price is required");
        }
        if(book.getStockQuantity()== null || book.getStockQuantity()< 0){
            throw new InvalidInputException("Valid stock quantity is required ");
        }
    }
}
