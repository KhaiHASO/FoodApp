package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.Supplier;

public interface SupplierService {
    Supplier saveSupplier(Supplier supplier);
    List<Supplier> getAllSuppliers();
    Supplier getSupplierById(int id);
    Supplier updateSupplier(Supplier supplier, int id);
    void deleteSupplier(int id);
}
