/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
     


@Provider
public class GlobalExceptionMapper implements ExceptionMapper<RuntimeException> {
    
    
    @Override
    public Response toResponse(RuntimeException exception){
        ErrorResponse errorResponse =  new ErrorResponse(exception.getMessage());
        
        if(exception instanceof NotFoundException){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        
        }
        
        
        if(exception instanceof InvalidInputException){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        
        if(exception instanceof OutOfStockException){
            return Response.status(Response.Status.CONFLICT)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("An Unexpected error occured"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        
    }
    
    private static class ErrorResponse{
        private final String message;
        
        
        public  ErrorResponse(String message){
            this.message = message;
        }
        
        public String getMessage(){
            return message;
        }
    }  
}
