package antun.bart.onlinebookstore.service.impl;

import antun.bart.onlinebookstore.exception.CustomerNotFoundException;
import antun.bart.onlinebookstore.model.entity.Customer;
import antun.bart.onlinebookstore.repository.CustomerRepository;
import antun.bart.onlinebookstore.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    public static final int LOYALTY_BONUS_LIMIT = 10;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Integer getCustomerLoyaltyPoints(Integer customerId) {
        return customerRepository.getLoyaltyPointsByCustomerId(customerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    @Transactional
    @Override
    public Customer updateLoyaltyPoints(Customer customer, Integer points) {
        customer.setLoyaltyPoints(points);
        return customerRepository.save(customer);
    }
}
