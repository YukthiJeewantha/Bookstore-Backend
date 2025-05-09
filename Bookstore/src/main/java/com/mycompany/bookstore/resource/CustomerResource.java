/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import java.net.URI;
import java.util.List;

import com.mycompany.bookstore.JakartaRestConfiguration;
import com.mycompany.bookstore.model.Customer;
import com.mycompany.bookstore.service.CustomerService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    
    private final CustomerService customerService;
    
    public CustomerResource() {
        this.customerService = new CustomerService(JakartaRestConfiguration.getDataStore());
    }
    
    @GET
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    
    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") Long id) {
        return customerService.getCustomer(id);
    }
    
    @POST
    public Response createCustomer(Customer customer) {
        Customer created = customerService.createCustomer(customer);
        return Response.created(URI.create("/customers/" + created.getId()))
                .entity(created)
                .build();
    }
    
    @PUT
    @Path("/{id}")
    public Customer updateCustomer(@PathParam("id") Long id, Customer customer) {
        return customerService.updateCustomer(id, customer);
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") Long id) {
        customerService.deleteCustomer(id);
        return Response.noContent().build();
    }
}
