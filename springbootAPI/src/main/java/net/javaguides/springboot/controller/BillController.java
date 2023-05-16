package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.BillViewDTO;
import net.javaguides.springboot.repository.BillViewRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillController {

    private final BillViewRepository billViewRepository;

    public BillController(BillViewRepository billViewRepository) {
        this.billViewRepository = billViewRepository;
    }

    @GetMapping
    public List<BillViewDTO> getCartProducts() {
        return billViewRepository.getBillView();
    }

    @GetMapping("/{customerId}")
    public List<BillViewDTO> getBillViewByCustomerId(@PathVariable String customerId) {
        return billViewRepository.getBillViewByCustomerId(customerId);
    }
}
