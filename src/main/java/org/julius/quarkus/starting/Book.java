package org.julius.quarkus.starting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.Instant;
import java.util.Random;

@AllArgsConstructor
@Getter
@Setter
public class Book {

    private int id;
    private String isbn13;
    private String title;
    private String author;
    private String genre;
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
