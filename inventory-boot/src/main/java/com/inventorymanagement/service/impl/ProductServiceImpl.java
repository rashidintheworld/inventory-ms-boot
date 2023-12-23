package com.inventorymanagement.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventorymanagement.dto.ProductDTO;
import com.inventorymanagement.entity.Product;
import com.inventorymanagement.exception.NotFoundException;
import com.inventorymanagement.repo.ProductRepository;
import com.inventorymanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public ProductDTO getProductById(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			return convertToDTO(optionalProduct.get());
		} else {
			throw new NotFoundException("Product not found");
		}
	}

	@Override
	@Transactional
	public ProductDTO createProduct(ProductDTO productDTO) {
		Product product = convertToEntity(productDTO);
		product = productRepository.save(product);
		return convertToDTO(product);
	}

	@Override
	@Transactional
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setName(productDTO.getName());
			product.setPrice(productDTO.getPrice());
			product = productRepository.save(product);
			return convertToDTO(product);
		} else {
			throw new NotFoundException("Product not found");
		}
	}

	@Override
	@Transactional
	public boolean deleteProduct(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			productRepository.deleteById(id);
			return true;
		} else {
			throw new NotFoundException("Product not found");
		}
	}

	private ProductDTO convertToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		// Set other fields as needed
		return productDTO;
	}

	private Product convertToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		return product;
	}
}
