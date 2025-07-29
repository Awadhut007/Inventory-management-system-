package com.example.demo.Service;

import com.example.demo.DTO.StockHistoryDTO;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.StockHistory;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Repository.StockHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockHistoryRepository stockHistoryRepository;

    public String addStock(Long productId, int quantity, String reason) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            return "Product not found.";
        }

        Product product = optionalProduct.get();
        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);

        logStockChange(productId, quantity, "ADD", reason);
        return "Stock added successfully.";
    }

    public String removeStock(Long productId, int quantity, String reason) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            return "Product not found.";
        }

        Product product = optionalProduct.get();
        if (product.getQuantity() < quantity) {
            return "Insufficient stock to remove.";
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        logStockChange(productId, -quantity, "REMOVE", reason);
        return "Stock removed successfully.";
    }

    public String autoDeductStock(Long productId, int quantity, String reason) {
        return removeStock(productId, quantity, reason != null ? reason : "Sale");
    }

    public List<StockHistory> getStockHistory(Long productId) {
        return stockHistoryRepository.findByProductIdOrderByTimestampDesc(productId);
    }

    private void logStockChange(Long Id, int quantity, String reason, String action) {
        StockHistory history = new StockHistory();
        history.setId(Id);
        history.setQuantity(quantity);
        history.setAction(action);
        history.setReason(reason);
        history.setTimestamp(LocalDateTime.now());
        stockHistoryRepository.save(history);
    }
    
    public List<StockHistoryDTO> getStockHistoryByReason(String reason) {
        List<StockHistory> historyList = stockHistoryRepository.findByReason(reason);
        return historyList.stream()
                .map(h -> new StockHistoryDTO(
                		h.getId(),
                        h.getProduct().getName(), // ✅ name
                        h.getQuantity(),                            // ✅ quantity
                        h.getAction(),                              // ✅ action
                        h.getReason(),
                        h.getTimestamp()
                ))
                .collect(Collectors.toList());
    }

}

