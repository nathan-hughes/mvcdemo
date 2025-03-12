package example.mvcdemo.repository;

import example.mvcdemo.model.Customer;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ListCrudRepository<Customer, Integer> {
}
