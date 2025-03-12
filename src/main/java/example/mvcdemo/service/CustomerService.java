package example.mvcdemo.service;

import java.util.*;

import example.mvcdemo.model.Customer;
import example.mvcdemo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer retrieveById(Integer id) {
        // check exception handler
        if (id == 666) {
            throw new RuntimeException("ka-boom");
        }
        Optional<Customer> opt = customerRepository.findById(id);
        return opt.orElseThrow(ResourceNotFoundException::new);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
