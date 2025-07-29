package com.example.demo.Service;

import com.example.demo.Entity.*;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    // Create a sale
    public Sale recordSale(Long productId, int quantitySold) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null || product.getQuantity() < quantitySold) {
            throw new IllegalArgumentException("Invalid product or insufficient stock");
        }

        // Deduct stock
        product.setQuantity(product.getQuantity() - quantitySold);
        productRepository.save(product);

        // Create sale record
        double totalPrice = quantitySold * product.getPrice();
        Sale sale = new Sale(productId, quantitySold, totalPrice, LocalDateTime.now(),product);
        return saleRepository.save(sale);
    }

    // Get all sales
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}
