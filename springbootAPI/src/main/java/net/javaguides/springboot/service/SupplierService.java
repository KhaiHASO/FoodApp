package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.Supplier;

public interface SupplierService {
    Supplier saveSupplier(Supplier supplier);
    List<Supplier> getAllSuppliers();
    Supplier getSupplierById(long id);
    Supplier updateSupplier(Supplier supplier, long id);
    void deleteSupplier(long id);
}
