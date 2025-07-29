package com.example.demo.Repository;


import com.example.demo.Entity.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {

    // Find all stock changes for a product, ordered by timestamp (newest first)
    List<StockHistory> findByProductIdOrderByTimestampDesc(Long productId);
    List<StockHistory> findByReason(String reason);
}

