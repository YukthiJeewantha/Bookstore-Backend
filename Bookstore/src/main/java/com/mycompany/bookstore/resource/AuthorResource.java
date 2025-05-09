/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.JakartaRestConfiguration;
import com.mycompany.bookstore.model.Author;
import com.mycompany.bookstore.model.Book;
import com.mycompany.bookstore.service.AuthorService;
import com.mycompany.bookstore.service.BookService;
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
import java.net.URI;
import java.util.List;



@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    private final AuthorService authorSerive;
    private final BookService bookService;

    public AuthorResource() {
        DataStore dataStore = JakartaRestConfiguration.getDataStore();
        this.authorSerive = new AuthorService(dataStore);
        this.bookService = new BookService(dataStore);
    }
    
    @GET
    public List<Author> getAllAuthors(){
        return authorSerive.getAllAuthors();
    }
    
    @GET
    @Path("/{id}")
    public Author getAuthor(@PathParam("id") Long id){
        return authorSerive.getAuthor(id);
    }
    
    @GET
    @Path("/{id}/books")
    public List<Book> getAuthorBooks(@PathParam("id") Long id){
        return bookService.getBooksByAuthor(id);
    }
    
    @POST
    public Response createAuthor(Author author){
        Author created = authorSerive.createAuthor(author);
        return Response.created(URI.create("/authors/" + created.getId()))
                .entity(created)
                .build();
    }
    
    @PUT
    @Path("/{id}")
    public Author updateAuthor(@PathParam("id") Long id, Author author){
        return authorSerive.updateAuthor(id,author);
    }
    
    
    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") Long id){
        authorSerive.deleteAuthor(id);
        return Response.noContent().build();
    }
    
}
