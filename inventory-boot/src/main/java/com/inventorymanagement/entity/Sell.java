package com.inventorymanagement.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Sell {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long productId;

	@NotNull
	@Max(value = 1000)
	private Integer quantity;

	@PastOrPresent
	private Date orderDate;

	public Sell() {
		super();
	}

	public Sell(Long id, @NotNull Long productId, @NotNull @Max(1000) Integer quantity, @PastOrPresent Date orderDate) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.orderDate = orderDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Sell [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", orderDate=" + orderDate
				+ "]";
	}
}
