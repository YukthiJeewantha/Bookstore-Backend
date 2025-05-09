/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;


import com.mycompany.bookstore.JakartaRestConfiguration;
import com.mycompany.bookstore.model.Book;
import com.mycompany.bookstore.service.BookService;
import com.mycompany.bookstore.exception.InvalidInputException;
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
import java.net.URI;
import java.time.Year;
import java.util.List;


@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    
    private final BookService bookService;
    
    public BookResource() {
        this.bookService = new BookService(JakartaRestConfiguration.getDataStore());
    }
    
    @GET
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    
    @GET
    @Path("/{id}")
    public Book getBook(@PathParam("id") Long id) {
        return bookService.getBook(id);
    }
    
    @POST
    public Response createBook(Book book) {
        // Validate the publication year to ensure it's not in the future
        int currentYear = Year.now().getValue();
        if (book.getPublicationYear() > currentYear) {
            throw new InvalidInputException("Publication year cannot be in the future.");
        }

        // Proceed to create the book
        Book created = bookService.createBook(book);
        return Response.created(URI.create("/books/" + created.getId())) 
                .entity(created)
                .build();
    }
    
    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") Long id, Book book) {
        return bookService.updateBook(id, book);
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {
        bookService.deletebook(id);
        return Response.noContent().build();
    }
}

