package antun.bart.onlinebookstore.service;

import antun.bart.onlinebookstore.model.entity.Customer;

import java.util.List;

public interface CustomerService {

    /**
     * Get amount of customer Loyalty Bonus Points
     * @param loyaltyPoints
     * @return
     */
    Integer getCustomerLoyaltyPoints(Integer loyaltyPoints);

    /**
     *
     * @return
     */
    List<Customer> getAllCustomers();

    /**
     *
     * @param customerId
     * @return
     */
    Customer getCustomerById(Integer customerId);

    /**
     * update customer loyalty points after transaction
     * @param customer
     * @param points
     * @return
     */
    Customer updateLoyaltyPoints(Customer customer, Integer points);
}
