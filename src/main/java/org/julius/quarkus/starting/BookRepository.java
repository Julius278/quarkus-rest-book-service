package org.julius.quarkus.starting;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class BookRepository {
    public List<Book> getAllBooks() {
        return List.of(
                new Book(12, "hello", "world", "sci-fi", 2012),
                new Book(13, "hello", "world2", "sci-fi", 2013),
                new Book(14, "hello", "world3", "sci-fi", 2014)
        );
    }


}
