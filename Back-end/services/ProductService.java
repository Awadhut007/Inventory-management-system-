package com.example.demo.Service;

import com.example.demo.DTO.BillingRequest;
import com.example.demo.DTO.BillingResponse;
import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Create/Update
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get by ID
    public Product getProductById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        return optional.orElse(null);
    }

    // Delete by ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
 // ProductService.java (continued)

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Update only if incoming fields are not null
        if (updatedProduct.getName() != null) {
            existingProduct.setName(updatedProduct.getName());
        }
        if (updatedProduct.getPrice() != null) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }
        if (updatedProduct.getQuantity() != null) {
            existingProduct.setQuantity(updatedProduct.getQuantity());
        }
        if (updatedProduct.getCategory() != null) {
            existingProduct.setCategory(updatedProduct.getCategory());
        }
        return productRepository.save(existingProduct);
    }

    
    public List<Product> getLowStockProducts(int threshold) {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getQuantity() <= threshold)
                .toList();
    }
    
    public List<String> getAllCategories() {
        return productRepository.findDistinctCategories();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    
    public List<BillingResponse> generateBill(List<BillingRequest> requests) {
        List<BillingResponse> responses = new ArrayList<>();

        for (BillingRequest req : requests) {
            Optional<Product> optionalProduct = productRepository.findById(req.getProductId());

            if (optionalProduct.isEmpty()) {
                throw new RuntimeException("Product not found with ID: " + req.getProductId());
            }

            Product product = optionalProduct.get();

            if (product.getQuantity() < req.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            double amount = req.getQuantity() * product.getPrice();
            responses.add(new BillingResponse(product.getName(), req.getQuantity(), product.getPrice(), amount));

            // Decrease quantity from stock
            product.setQuantity(product.getQuantity() - req.getQuantity());
            productRepository.save(product);
        }

        return responses;
    }



}
