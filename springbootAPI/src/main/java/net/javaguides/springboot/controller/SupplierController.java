package net.javaguides.springboot.controller;

import java.util.List;

import net.javaguides.springboot.model.Supplier;
import net.javaguides.springboot.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        super();
        this.supplierService = supplierService;
    }

    // build create supplier REST API
    @PostMapping()
    public ResponseEntity<Supplier> saveSupplier(@RequestBody Supplier supplier){
        return new ResponseEntity<Supplier>(supplierService.saveSupplier(supplier), HttpStatus.CREATED);
    }

    // build get all suppliers REST API
    @GetMapping
    public List<Supplier> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }

    // build get supplier by id REST API
    // http://localhost:8080/api/suppliers/1
    @GetMapping("{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable("id") int supplierId){
        return new ResponseEntity<Supplier>(supplierService.getSupplierById(supplierId), HttpStatus.OK);
    }

    // build update supplier REST API
    // http://localhost:8080/api/suppliers/1
    @PutMapping("{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable("id") int id, @RequestBody Supplier supplier){
        return new ResponseEntity<Supplier>(supplierService.updateSupplier(supplier, id), HttpStatus.OK);
    }

    // build delete supplier REST API
    // http://localhost:8080/api/suppliers/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable("id") int id){

        // delete supplier from DB
        supplierService.deleteSupplier(id);

        return new ResponseEntity<String>("Supplier deleted successfully!.", HttpStatus.OK);
    }

}
