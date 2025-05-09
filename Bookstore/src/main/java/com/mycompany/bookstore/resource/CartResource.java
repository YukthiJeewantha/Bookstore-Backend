/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.JakartaRestConfiguration;
import com.mycompany.bookstore.model.CartItem;
import com.mycompany.bookstore.service.CartService;
import com.mycompany.bookstore.service.CustomerService;
import com.mycompany.bookstore.storage.DataStore;
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
import java.util.List;



@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    
    private final CartService cartService;
    
    public CartResource(){
        DataStore dataStore = JakartaRestConfiguration.getDataStore();
        CustomerService customerSerivce = new CustomerService(dataStore);
        this.cartService = new CartService(dataStore, customerSerivce);
    }
    
    @POST
    @Path("/items")
    public Response addToCart(@PathParam("customerId") Long customerId, CartItem item){
        cartService.addToCart(customerId,item);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @GET
    public List<CartItem> getCart(@PathParam("customerId") Long customerId){
        return cartService.getCart(customerId);
    
    }
    
    @PUT
    @Path("/items/{bookId}")
    public Response UpdateCartItem(@PathParam("customerId") Long customerId, @PathParam("bookId") Long bookId, CartItem item){
        cartService.updateCartItem(customerId, bookId, item);
        return Response.ok().build();
    }    
    
    @DELETE
    @Path("/items/{bookId}")
    public Response removeFromCart(@PathParam("customerId") Long customerId,@PathParam("bookId") Long bookId){
        cartService.removeFromCart(customerId,bookId);
        return Response.noContent().build();
    }
}
