package com.example.demo.Controller;

import com.example.demo.Entity.Sale;
import com.example.demo.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin
public class SaleController {

    @Autowired
    private SaleService saleService;

    // POST: Record a new sale
    @PostMapping
    public Sale recordSale(@RequestBody Map<String, Object> payload) {
        Long productId = Long.valueOf(payload.get("productId").toString());
        int quantity = Integer.parseInt(payload.get("quantitySold").toString());
        return saleService.recordSale(productId, quantity);
    }

    // GET: Get all sales
    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }
}
