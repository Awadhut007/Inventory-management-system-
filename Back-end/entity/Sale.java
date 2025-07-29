package com.example.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private int quantitySold;

    private double totalPrice;

    private LocalDateTime saleDate;
    
    @ManyToOne
    @JoinColumn(name = "product_id1")
    private Product product;

    // Constructors
    public Sale() {
    	super();
    }

    public Sale(Long productId, int quantitySold, double totalPrice, LocalDateTime saleDate, Product product) {
        this.productId = productId;
        this.quantitySold = quantitySold;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
    
    
}
