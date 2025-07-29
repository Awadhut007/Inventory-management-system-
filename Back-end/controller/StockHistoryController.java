package com.example.demo.Controller;

import com.example.demo.DTO.StockHistoryDTO;
import com.example.demo.Entity.StockHistory;
import com.example.demo.Service.StockService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin(origins = "http://localhost:3000") // adjust port if needed
public class StockHistoryController {

    @Autowired
    private StockService stockHistoryService;

    // ✅ Add stock with reason
    @PostMapping("/add")
    public String addStock(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam String reason
    ) {
        return stockHistoryService.addStock(productId, quantity, reason);
    }

    // ✅ Remove stock with reason
    @PostMapping("/remove")
    public String removeStock(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam String reason
    ) {
        return stockHistoryService.removeStock(productId, quantity, reason);
    }
    
 // ✅ Get stock history for a product
    @GetMapping("/history/{productId}")
    public List<StockHistory> getStockHistory(@PathVariable Long productId) {
        return stockHistoryService.getStockHistory(productId);
    }
    
    @GetMapping("/by-reason/{reason}")
    public ResponseEntity<List<StockHistoryDTO>> getStockByReason(@PathVariable String reason) {
        List<StockHistoryDTO> data = stockHistoryService.getStockHistoryByReason(reason);
        return ResponseEntity.ok(data);
    }
    
}
