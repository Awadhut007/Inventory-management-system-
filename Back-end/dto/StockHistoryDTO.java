package com.example.demo.DTO;

import java.time.LocalDateTime;

public class StockHistoryDTO {
	private Long id;
    private String name;
    private int quantity;
    private String action;
    private String reason;
    private LocalDateTime timestamp;
	public StockHistoryDTO( Long id,String name, int quantity, String action, String reason,
			LocalDateTime timestamp) {
		super();
		
		this.id=id;
		this.name = name;
		this.quantity = quantity;
		this.action = action;
		this.reason = reason;
		this.timestamp = timestamp;
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
	public void setName(String name) {
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

    // Constructors, Getters, Setters
    
    
}
