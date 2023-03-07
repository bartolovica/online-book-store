package antun.bart.onlinebookstore.repository;

import antun.bart.onlinebookstore.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT c.loyaltyPoints FROM Customer c " +
            "WHERE c.customerId = :customerId")
    Integer getLoyaltyPointsByCustomerId(@Param("customerId") Integer customerId);

    @Transactional
    @Modifying
    @Query("UPDATE Customer c " +
            "SET c.loyaltyPoints = :loyaltyPoints " +
            "WHERE c.customerId = :customerId")
    void updateLoyaltyPointsForCustomer(@Param("customerId") Integer customerId, @Param("loyaltyPoints") Integer loyaltyPoints);

}
