package org.julius.quarkus.starting;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository {

    @ConfigProperty(name = "books.genre")
    private String defaultGenre;

    public List<Book> getAllBooks() {
        return List.of(
                new Book(1, "Java ist auch eine Insel", "Christian Ullenboom", defaultGenre, 2003),
                new Book(2, "Modern Java in Action", "Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft", defaultGenre, 2017),
                new Book(3, "Beginning Quarkus Framework", "Tayo Koleoso", defaultGenre, 2020),
                new Book(4, "Der Erbe der Jedi-Ritter", "Kevin Hearne", "sci-fi", 2015)
        );
    }

    public Optional<Book> getBookById(int id) {
        return getAllBooks().stream().filter(book -> book.getId() == id).findFirst();
    }
}
