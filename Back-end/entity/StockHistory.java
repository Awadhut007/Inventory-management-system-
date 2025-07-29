package com.example.demo.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class StockHistory {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 private String name;
	    private int quantity;
	    private String action;
	    private String reason;
	    private LocalDateTime timestamp;
	 
	 @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;
	 
	 public StockHistory() {
		super();
		// TODO Auto-generated constructor stub
	 }

	 public StockHistory(Long id, String name, int quantity, String action, String reason,
			LocalDateTime timestamp, Product product) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.action = action;
		this.reason = reason;
		this.timestamp = timestamp;
		this.product = product;
	 }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public String getName() {
		 return name;
	 }

	 public void serName(String name) {
		 this.name = name;
	 }

	 public int getQuantity() {
		 return quantity;
	 }

	 public void setQuantity(int quantity) {
		 this.quantity = quantity;
	 }

	 public String getAction() {
		 return action;
	 }

	 public void setAction(String action) {
		 this.action = action;
	 }

	 public String getReason() {
		 return reason;
	 }

	 public void setReason(String reason) {
		 this.reason = reason;
	 }

	 public LocalDateTime getTimestamp() {
		 return timestamp;
	 }

	 public void setTimestamp(LocalDateTime timestamp) {
		 this.timestamp = timestamp;
	 }

	 public Product getProduct() {
		 return product;
	 }

	 public void setProduct(Product product) {
		 this.product = product;
	 }
	 
	 
	 
	 
	 
 
 
}

