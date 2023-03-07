package antun.bart.onlinebookstore.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "invoice_item")
@NoArgsConstructor
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private int itemId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount")
    private String discount;

    @Column(name = "discounted_price")
    private Double discountedPrice;

    @Column(name = "total_price")
    private Double totalPrice;

    public InvoiceItem(Book book, int quantity, Double price, String discount, Double discountedPrice, Double totalPrice) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.discountedPrice = discountedPrice;
        this.totalPrice = totalPrice;
    }
}
