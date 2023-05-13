package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Customer;
import net.javaguides.springboot.repository.CustomerRepository;
import net.javaguides.springboot.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
    }

    @Override
    public Customer updateCustomer(Customer customer, String id) {
        Customer existingCustomer = getCustomerById(id);

        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setFullname(customer.getFullname());
        existingCustomer.setPassword(customer.getPassword());
        existingCustomer.setPhoto(customer.getPhoto());
        existingCustomer.setRoleId(customer.getRoleId());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(String id) {
        Customer existingCustomer = getCustomerById(id);
        customerRepository.delete(existingCustomer);
    }

    @Override
    public Customer login(String username, String password) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByUsernameAndPassword(username, password);
        return optionalCustomer.orElse(null);
    }
}
