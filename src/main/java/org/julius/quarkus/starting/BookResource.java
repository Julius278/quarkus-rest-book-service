package org.julius.quarkus.starting;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.julius.quarkus.starting.numbers.NumberProxy;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Book REST Service")
public class BookResource {

    @RestClient
    NumberProxy numberProxy;

    @Inject
    BookRepository bookRepository;

    @Inject
    Logger logger;

    @Retry(delay = 2000, maxRetries = 3)
    @Fallback(fallbackMethod = "getExampleHelloWorldBookFallback")
    @Path("/world")
    @GET
    @Operation(summary = "getExampleHelloWorldBook", description = "returns a specific 'hello world' test book with all parameters")
    public Response getExampleHelloWorldBook() {
        logger.info("getExampleHelloWorldBook");
        String isbn13 = numberProxy.generateRandomIsbnThirteen().getIsbn13();
        return Response.status(200).entity(new Book(12, isbn13, "hello", "world", "sci-fi", 2012)).build();
    }

    public Response getExampleHelloWorldBookFallback() {
        logger.warn("getExampleHelloWorldBookFallback, fallback ISBN number set");
        String isbn13 = "13-fallback";

        //TODO status 206 fails in test
        return Response.status(200).entity(new Book(12, isbn13, "hello", "world", "sci-fi", 2012)).build();
    }

    @GET
    @Operation(summary = "getAllBooks", description = "returns a list of all known books in this service")
    public List<Book> getAllBooks() {
        logger.info("getAllBooks");
        return bookRepository.getAllBooks();
    }

    @Fallback(fallbackMethod = "createBookFallback")
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.MEDIA_TYPE_WILDCARD})
    @Operation(summary = "createBook", description = "endpoint to create a new book")
    public Response createBook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("yearOfPublication") int yearOfPublication, @FormParam("genre") String genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn13(numberProxy.generateRandomIsbnThirteen().getIsbn13());
        book.setAuthor(author);
        book.setYearOfPublication(yearOfPublication);
        book.setGenre(genre);

        logger.info("new Book created with id: " + book.getId());

        return Response.status(201).entity(book).build();
    }

    public Response createBookFallback(@FormParam("title") String title, @FormParam("author") String author, @FormParam("yearOfPublication") int yearOfPublication, @FormParam("genre") String genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn13("13-fallback will be set later");
        book.setAuthor(author);
        book.setYearOfPublication(yearOfPublication);
        book.setGenre(genre);

        logger.warn("numbers service did not respond, fallback isbn number set");
        logger.warn("new Book created, id: " + book.getId());

        //TODO status 206 fails in test
        return Response.status(201).entity(book).build();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "countAllBooks", description = "returns the total number of books")
    public int countAllBooks() {
        return getAllBooks().size();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "getBookById", description = "returns a specific book by its ID")
    public Optional<Book> getBookById(@PathParam("id") int id) {
        return bookRepository.getBookById(id);
    }
}