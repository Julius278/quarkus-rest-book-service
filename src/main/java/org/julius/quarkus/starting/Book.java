package org.julius.quarkus.starting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Book {

    private int id;
    private String title;
    private String author;
    private String genre;
    private int yearOfPublication;

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
