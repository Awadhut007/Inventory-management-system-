package com.example.demo.DTO;

public class BillingResponse {
    private String productName;
    private int quantity;
    private double price;
    private double amount;
	public BillingResponse(String productName, int quantity, Double price, double amount) {
		super();
		// TODO Auto-generated constructor stub
	}
	public BillingResponse(String productName, int quantity, double price, double amount) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

    // Constructor, Getters and Setters
    
}

