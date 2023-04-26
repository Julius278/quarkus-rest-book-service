package org.julius.quarkus.starting;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

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

    @Inject
    BookRepository bookRepository;

    @Inject
    Logger logger;

    @Path("/world")
    @GET
    @Operation(summary = "getExampleHelloWorldBook", description = "returns a specific 'hello world' test book with all parameters")
    public Book getExampleHelloWorldBook() {
        logger.info("getExampleHelloWorldBook");
        return new Book(12, "13-000000000", "hello", "world", "sci-fi", 2012);
    }

    @GET
    @Operation(summary = "getAllBooks", description = "returns a list of all known books in this service")
    public List<Book> getAllBooks() {
        logger.info("getAllBooks");
        return bookRepository.getAllBooks();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.MEDIA_TYPE_WILDCARD})
    @Operation(summary = "createBook", description = "endpoint to create a new book")
    public Response createBook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("yearOfPublication") int yearOfPublication, @FormParam("genre") String genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn13("13- will set that via numbers microservice");
        book.setAuthor(author);
        book.setYearOfPublication(yearOfPublication);
        book.setGenre(genre);

        logger.info("new Book created, id: " + book.getId());

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