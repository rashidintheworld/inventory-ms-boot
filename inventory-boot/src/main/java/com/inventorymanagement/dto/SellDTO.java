package com.inventorymanagement.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class SellDTO implements Serializable {
	private Long id;

	@NotNull(message = "Product ID is required")
	private Long productId;

	@NotNull(message = "Quantity is required")
	@Max(value = 1000, message = "Quantity cannot exceed 1000")
	private Integer quantity;

	@PastOrPresent(message = "Order date must be in the past or present")
	private Date orderDate;

	public SellDTO() {
		super();
	}

	public SellDTO(Long id, @NotNull(message = "Product ID is required") Long productId,
			@NotNull(message = "Quantity is required") @Max(value = 1000, message = "Quantity cannot exceed 1000") Integer quantity,
			@PastOrPresent(message = "Order date must be in the past or present") Date orderDate) {
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
		return "SellDTO [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", orderDate=" + orderDate
				+ "]";
	}
}
