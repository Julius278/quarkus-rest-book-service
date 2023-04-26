package org.julius.quarkus.starting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.Instant;
import java.util.Random;

@AllArgsConstructor
@Getter
@Setter
@Schema(description = "book implementation")
public class Book {

    private int id;
    @Schema(example = "13-000000")
    private String isbn13;
    @Schema(required = true, description = "title of the book", example = "George Lucas")
    private String title;
    @Schema(required = true, description = "author of the book")
    private String author;
    @Schema(required = true, description = "genre of the book", example = "sci-fi")
    private String genre;
    @Schema(example = "2012")
    private int yearOfPublication;
    @JsonbDateFormat("dd-MM-YYYY hh:mm:ss")
    private Instant creationDate;

    public Book(int id, String isbn13, String title, String author, String genre, int yearOfPublication) {
        this.id = id;
        this.isbn13 = isbn13;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearOfPublication = yearOfPublication;
        this.creationDate = Instant.now();
    }

    public Book() {
        this.id = new Random().nextInt(1_000_000);
        this.creationDate = Instant.now();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                '}';
    }
}
