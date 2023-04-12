package org.julius.quarkus.starting;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.jboss.logging.Logger;

import javax.inject.Inject;
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

    @Inject
    private BookRepository bookRepository;

    @Inject
    private Logger logger;

    @Path("/plain")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @Path("/world")
    @GET
    public Book getExampleHelloWorldBook() {
        logger.info("getExampleHelloWorldBook");
        return new Book(12, "hello", "world", "sci-fi", 2012);
    }

    @GET
    public List<Book> getAllBooks() {
        logger.info("getAllBooks");
        return bookRepository.getAllBooks();
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
        return bookRepository.getBookById(id);
    }
}