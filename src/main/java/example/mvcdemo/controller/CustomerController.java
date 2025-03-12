package example.mvcdemo.controller;

import java.util.*;
import example.mvcdemo.model.Customer;
import example.mvcdemo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public Customer findCustomerById(@PathVariable("customerId") int customerId) {
        return customerService.retrieveById(customerId);
    }

    @GetMapping("/")
    public List<Customer> findAllCustomers() {
        return customerService.findAll();
    }

    @PostMapping("/")
    public void addCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
    }
}