package antun.bart.onlinebookstore.service;

import antun.bart.onlinebookstore.model.entity.Customer;

import java.util.List;

public interface CustomerService {

    Integer getCustomerLoyaltyPoints(Integer loyaltyPoints);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer customerId);

    Customer updateLoyaltyPoints(Customer customer, Integer points);
}
