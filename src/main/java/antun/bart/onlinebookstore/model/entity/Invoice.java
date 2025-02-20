package antun.bart.onlinebookstore.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "Invoice")
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id", nullable = false)
    private int invoiceId;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceItem> items;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "loyalty_bonus")
    private Boolean loyaltyBonus;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "created_date")
    private String createdDate;

    public Invoice(List<InvoiceItem> items, Customer customer, Boolean loyaltyBonus, Double totalPrice, String createdDate) {
        this.items = items;
        this.customer = customer;
        this.loyaltyBonus = loyaltyBonus;
        this.totalPrice = totalPrice;
        this.createdDate = createdDate;
    }
}
