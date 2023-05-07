package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}

