package com.inventorymanagement.dto;

import java.io.Serializable;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductDTO implements Serializable {
	private Long id;

	@NotEmpty(message = "Product name is required")
	@Size(min = 3, max = 255, message = "Product name must be between 3 and 255 characters")
	private String name;

	@DecimalMin(value = "0.01", message = "Product price must be greater than 0.01")
	private Double price;

	public ProductDTO() {
		super();
	}

	public ProductDTO(Long id,
			@NotEmpty(message = "Product name is required") @Size(min = 3, max = 255, message = "Product name must be between 3 and 255 characters") String name,
			@DecimalMin(value = "0.01", message = "Product price must be greater than 0.01") Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}
