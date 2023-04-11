package org.julius.quarkus.starting;

public class Book {

    private int id;

    private String title;

    private String author;
    private String genre;
    private int yearOfPublication;
    public Book(int id, String title, String author, String genre, int yearOfPublication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearOfPublication = yearOfPublication;
    }

}
