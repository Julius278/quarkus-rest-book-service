package org.julius.quarkus.starting;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterForReflection
@Path("/api/books")
@Produces(MediaType.TEXT_PLAIN)
public class BookResource {

    @Path("/plain")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @Path("/world")
    @GET
    public Book helloWorldBook() {
        Book book = new Book(12, "hello", "world", "sci-fi", 2012);
        System.out.println(book.toString());
        return book;
    }

    @GET
    public List<Book> getBooks() {
        return List.of(
                new Book(12, "hello", "world", "sci-fi", 2012),
                new Book(13, "hello", "world", "sci-fi", 2013),
                new Book(14, "hello", "world", "sci-fi", 2014)
        );
    }
}