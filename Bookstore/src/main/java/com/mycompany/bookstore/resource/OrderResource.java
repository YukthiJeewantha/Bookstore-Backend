/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import java.net.URI;
import java.util.List;

import com.mycompany.bookstore.JakartaRestConfiguration;
import com.mycompany.bookstore.model.Order;
import com.mycompany.bookstore.service.CartService;
import com.mycompany.bookstore.service.CustomerService;
import com.mycompany.bookstore.service.OrderService;
import com.mycompany.bookstore.storage.DataStore;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    
    private final OrderService orderService;
    
    public OrderResource() {
        DataStore dataStore = JakartaRestConfiguration.getDataStore();
        CustomerService customerService = new CustomerService(dataStore);
        CartService cartService = new CartService(dataStore, customerService);
        this.orderService = new OrderService(dataStore, customerService, cartService);
    }
    
    @GET
    public List<Order> getCustomerOrders(@PathParam("customerId") Long customerId) {
        return orderService.getCustomerOrders(customerId);
    }
    
    @GET
    @Path("/{orderId}")
    public Order getOrder(
            @PathParam("customerId") Long customerId,
            @PathParam("orderId") Long orderId) {
        return orderService.getOrder(customerId, orderId);
    }
    
    @POST
    public Response createOrder(@PathParam("customerId") Long customerId) {
        Order order = orderService.createOrder(customerId);
        return Response.created(URI.create("/customers/" + customerId + "/orders/" + order.getOrderId()))
                .entity(order)
                .build();
    }
}
