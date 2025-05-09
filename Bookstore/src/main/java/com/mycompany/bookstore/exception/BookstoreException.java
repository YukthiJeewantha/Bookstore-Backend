/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.exception;


public class BookstoreException extends RuntimeException{
    private final String errorCode;
    
    public BookstoreException(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;
        
    }
    
    public String getErrorCode(){
        return errorCode;
    }
}

