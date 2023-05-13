package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("SELECT c FROM Customer c WHERE c.customerId = :username AND c.password = :password")
    Optional<Customer> findCustomerByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
