package org.julius.quarkus.starting;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

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
        return new Book(12, "13-000000000", "hello", "world", "sci-fi", 2012);
    }

    @GET
    public List<Book> getAllBooks() {
        logger.info("getAllBooks");
        return bookRepository.getAllBooks();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.MEDIA_TYPE_WILDCARD})
    public Response createBook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("yearOfPublication") int yearOfPublication, @FormParam("genre") String genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn13("will set that via numbers microservice");
        book.setAuthor(author);
        book.setYearOfPublication(yearOfPublication);
        book.setGenre(genre);

        logger.info("new Book created, id: " + book.getId());

        return Response.status(201).entity(book).build();
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