package antun.bart.onlinebookstore.controller;

import antun.bart.onlinebookstore.model.entity.Customer;
import antun.bart.onlinebookstore.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static antun.bart.onlinebookstore.controller.ControllerDefinitions.CUSTOMER_API;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(CUSTOMER_API)
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Integer> getLoyaltyPointsForCustomer(@PathVariable("customerId") Integer customerId) {
        return ok(customerService.getCustomerLoyaltyPoints(customerId));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ok(customerService.getAllCustomers());
    }
}
