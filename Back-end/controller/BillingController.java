package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.BillingRequest;
import com.example.demo.DTO.BillingResponse;
import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.ProductService;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductService productService;

    @PostMapping("/purchase")
    public ResponseEntity<String> purchase(@RequestBody List<BillingRequest> billingList) {
        double totalAmount = 0;

        for (BillingRequest item : billingList) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getQuantity() < item.getQuantity()) {
                return ResponseEntity.badRequest().body("Insufficient stock for: " + product.getName());
            }

            double itemTotal = product.getPrice() * item.getQuantity();
            totalAmount += itemTotal;

            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);
        }

        return ResponseEntity.ok("Total Bill Amount: ₹" + totalAmount);
    }
    
    @PostMapping("/generate")
    public List<BillingResponse> generateBill(@RequestBody List<BillingRequest> requests) {
        return productService.generateBill(requests);
    }
}
