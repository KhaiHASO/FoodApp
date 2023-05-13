package net.javaguides.springboot.controller;

import java.util.List;

import net.javaguides.springboot.model.Customer;
import net.javaguides.springboot.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    // create customer REST API
    @PostMapping()
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }

    // get all customers REST API
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // get customer by id REST API
    // http://localhost:8080/api/customers/1
    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String customerId) {
        return new ResponseEntity<Customer>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    // update customer REST API
    // http://localhost:8080/api/customers/1
    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.updateCustomer(customer, id), HttpStatus.OK);
    }

    // delete customer REST API
    // http://localhost:8080/api/customers/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") String id) {

        // delete customer from DB
        customerService.deleteCustomer(id);

        return new ResponseEntity<String>("Customer deleted successfully!.", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody Customer loginRequest) {
        String username = loginRequest.getCustomerId();
        String password = loginRequest.getPassword();

        Customer customer = customerService.login(username, password);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
