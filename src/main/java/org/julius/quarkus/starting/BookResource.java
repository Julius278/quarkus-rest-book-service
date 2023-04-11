package org.julius.quarkus.starting;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RegisterForReflection
@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Path("/plain")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @Path("/world")
    @GET
    public Book getExampleHelloWorldBook() {
        return new Book(12, "hello", "world", "sci-fi", 2012);
    }

    @GET
    public List<Book> getAllBooks() {
        return List.of(
                new Book(12, "hello", "world", "sci-fi", 2012),
                new Book(13, "hello", "world2", "sci-fi", 2013),
                new Book(14, "hello", "world3", "sci-fi", 2014)
        );
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countAllBooks() {
        return getAllBooks().size();
    }

    @GET
    @Path("/{id}")
    public Optional<Book> getBookById(@PathParam("id") int id) {
        return getAllBooks().stream().filter(book -> book.getId() == id).findFirst();
    }
}