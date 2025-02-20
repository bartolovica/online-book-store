package antun.bart.onlinebookstore.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Table(name = "INVENTORY")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id", nullable = false)
    private Integer inventoryId;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "item_quantity")
    private Integer itemQuantity;

    public Inventory(Book book, int itemQuantity) {
        this.book = book;
        this.itemQuantity = itemQuantity;
    }
}


