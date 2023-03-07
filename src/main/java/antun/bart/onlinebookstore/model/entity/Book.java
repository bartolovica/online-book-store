package antun.bart.onlinebookstore.model.entity;

import antun.bart.onlinebookstore.model.enums.BookType;
import antun.bart.onlinebookstore.model.enums.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Enumerated(EnumType.STRING)
    private BookType bookType;

    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "author")
    private String author;

    public Book(String bookName, BookType bookType, double price, Genre genre, String author) {
        this.bookName = bookName;
        this.bookType = bookType;
        this.price = price;
        this.genre = genre;
        this.author = author;
    }
}
