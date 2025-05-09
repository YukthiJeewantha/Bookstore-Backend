/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.service;

import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.exception.NotFoundException;
import com.mycompany.bookstore.model.Author;
import com.mycompany.bookstore.storage.DataStore;
import java.util.List;



public class AuthorService {
    
    private final DataStore dataStore;
    
    public AuthorService(DataStore dataStore){
        this.dataStore  =  dataStore;
    }
    
    public List<Author> getAllAuthors(){
        return dataStore.getAllAuthors();
    }
    
    public Author getAuthor(Long id){
        Author author  = dataStore.getAuthor(id);
        if(author == null){
            throw new NotFoundException("Author not found with Id: "+ id);
        }
        
        return author;
    }
    
    public Author createAuthor(Author author){
        validateAuthor(author);
        return dataStore.addAuthor(author);
    }
    
    public void deleteAuthor(Long id){
        getAuthor(id);
        if(!dataStore.getBooksByAuthor(id).isEmpty()){
            throw new InvalidInputException("Cannot delete author with exsisting books");
        }
        
        dataStore.removeAuthor(id);
    }
    
    public Author updateAuthor(Long id, Author author) {
        validateAuthor(author);
        getAuthor(id); // Will throw NotFoundException if author doesn't exist
        
        author.setId(id);
        return dataStore.addAuthor(author);
    }
    
    private void validateAuthor(Author author){
        if(author == null){
            throw new InvalidInputException("Author cannot be null");
        }
        
        if(author.getName()== null || author.getName().trim().isEmpty()){
            throw new InvalidInputException("Author name is required");
        }
    }
}
