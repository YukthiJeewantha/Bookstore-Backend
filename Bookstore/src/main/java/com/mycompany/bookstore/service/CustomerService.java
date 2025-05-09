/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.service;

import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.exception.NotFoundException;
import com.mycompany.bookstore.model.Customer;
import com.mycompany.bookstore.storage.DataStore;
import java.util.List;


public class CustomerService {
    
    private final DataStore dataStore;
    
    public CustomerService(DataStore dataStore){
        this.dataStore  =  dataStore;
    }
    
    public List<Customer> getAllCustomers(){
        return dataStore.getAllCustomers();
    }
    
    public Customer getCustomer(Long id){
        Customer customer  = dataStore.getCustomer(id);
        if(customer == null){
            throw new NotFoundException("Customer not found with Id: "+ id);
        }
        
        return customer;
    }
    
    public Customer createCustomer(Customer customer){
        validateCustomer(customer);
        return dataStore.addCustomer(customer);
    }
    
    public Customer updateCustomer(Long id, Customer customer){
        validateCustomer(customer);
        getCustomer(id);
        
        customer.setId(id);
        return dataStore.addCustomer(customer);
    
    }
    
    public void deleteCustomer(Long id){
        getCustomer(id);
        
      
        dataStore.removeCustomer(id);
    }
    
    private void validateCustomer(Customer customer){
        if(customer == null){
            throw new InvalidInputException("Customer cannot be null");
        }
        
        if(customer.getName()== null || customer.getName().trim().isEmpty()){
            throw new InvalidInputException("Customer name is required");
        }
        if(customer.getEmail() == null || customer.getEmail().trim().isEmpty()){
            throw new InvalidInputException("Email cannot be null");
        }
        if(customer.getPassword() == null || customer.getPassword().trim().isEmpty()){
            throw new InvalidInputException("Password cannot be null");
        }
        if(!customer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            throw new InvalidInputException("Invalid email format");
        }
    }
}   
