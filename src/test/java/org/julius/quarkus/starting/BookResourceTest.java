package org.julius.quarkus.starting;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.internal.http.Status;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.hasKey;

@QuarkusTest
public class BookResourceTest {
    @Test
    public void testExampleHelloWorldEndpoint(){
        given().accept(MediaType.APPLICATION_JSON)
                .when()
                .get("/api/books/world")
                .then()
                .statusCode(Status.SUCCESS.ordinal())
                .body("author", is("world"))
                .body("genre", is("sci-fi"))
                .body("title", is("hello"))
                .body("isbn13", startsWith("13-"))
                .body("yearOfPublication", is(2012))
                .body("$", hasKey("creationDate")); //creationDate ist present
    }

    @Test
    void shouldReturnAllBooks() {
        given().header("ACCEPT", MediaType.APPLICATION_JSON)
                .when()
                    .get("/api/books")
                .then()
                    .statusCode(200)
                    .body("size()", is(4));
    }


    @Test
    void shouldCountAllBooks(){
        String numberOfBooks = "4";

        given().accept(MediaType.TEXT_PLAIN)
                .when()
                    .get("/api/books/count")
                .then()
                    .statusCode(200)
                    .body(is(numberOfBooks));
    }

    @Test
    void shouldReturnBookWithId(){
        int id = 3;
        given()
                .accept(MediaType.APPLICATION_JSON)
                .pathParam("id", id)
                .when()
                    .get("/api/books/{id}")
                .then()
                    .statusCode(200)
                    .body("id", is(id))
                    .body("author", is("Tayo Koleoso"))
                    .body("title", is("Beginning Quarkus Framework"))
                    .body("yearOfPublication", is(2020))
                    .body("isbn13", startsWith("13-"))
                    .body("genre", is("IT"));
    }

    @Test
    void createBook() {
        String title = "Test123";
        String author = "George Lucas";
        int yearOfPublication = 2012;
        String genre = "crime";

        given()
                .formParam("title", title)
                .formParam("author", author)
                .formParam("yearOfPublication", yearOfPublication)
                .formParam("genre", genre)
        .when()
                .post("/api/books")
        .then()
                .statusCode(201)
                .body("genre", is(genre))
                .body("title", is(title))
                .body("author", is(author))
                .body("yearOfPublication", is(yearOfPublication))
                .body("isbn13", startsWith("13-"));
    }
}