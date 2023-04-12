package org.julius.quarkus.starting;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void testHelloEndpoint() {
        given().accept(MediaType.TEXT_PLAIN)
          .when()
                .get("/api/books/plain")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

    @Test
    public void testExampleHelloWorldEndpoint(){
        given().accept(MediaType.APPLICATION_JSON)
                .when()
                .get("/api/books/world")
                .then()
                .statusCode(200)
                .body("author", is("world"))
                .body("genre", is("sci-fi"))
                .body("title", is("hello"))
                .body("yearOfPublication", is(2012));
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
                    .body("genre", is("IT"));
    }
}