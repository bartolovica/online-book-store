package antun.bart.onlinebookstore.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "loyalty_points", nullable = false)
    private Integer loyaltyPoints;

    public Customer(String username, String email, Integer loyaltyPoints) {
        this.username = username;
        this.email = email;
        this.loyaltyPoints = loyaltyPoints;
    }
}
