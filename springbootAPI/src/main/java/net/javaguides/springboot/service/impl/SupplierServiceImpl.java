package net.javaguides.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Supplier;
import net.javaguides.springboot.repository.SupplierRepository;
import net.javaguides.springboot.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        super();
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier getSupplierById(int id) {
        return supplierRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Supplier", "Id", id));
    }

    @Override
    public Supplier updateSupplier(Supplier supplier, int id) {
        // we need to check whether supplier with given id is exist in DB or not
        Supplier existingSupplier = supplierRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Supplier", "Id", id));

        existingSupplier.setName(supplier.getName());
        existingSupplier.setEmail(supplier.getEmail());
        existingSupplier.setPhone(supplier.getPhone());
        existingSupplier.setImage(supplier.getImage());

        // save existing supplier to DB
        supplierRepository.save(existingSupplier);
        return existingSupplier;
    }

    @Override
    public void deleteSupplier(int id) {
        // check whether a supplier exist in a DB or not
        supplierRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Supplier", "Id", id));
        supplierRepository.deleteById(id);
    }

}
