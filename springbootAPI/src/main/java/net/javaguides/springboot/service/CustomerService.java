package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(String id);
    Customer updateCustomer(Customer customer, String id);
    void deleteCustomer(String id);
    Customer login(String username, String password);
}
