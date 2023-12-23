package com.inventorymanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventorymanagement.dto.SellDTO;
import com.inventorymanagement.service.SellService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sells")
public class SellController {
	
	@Autowired
	private SellService sellService;

	@GetMapping
	public ResponseEntity<List<SellDTO>> getAllSells() {
		List<SellDTO> sells = sellService.getAllSells();
		return new ResponseEntity<>(sells, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SellDTO> getSellById(@PathVariable Long id) {
		SellDTO sell = sellService.getSellById(id);
		return new ResponseEntity<>(sell, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SellDTO> createSell(@Valid @RequestBody SellDTO sellDTO) {
		SellDTO createdSell = sellService.createSell(sellDTO);
		return new ResponseEntity<>(createdSell, HttpStatus.CREATED);
	}

	@GetMapping("/products-sold-last-month")
	public ResponseEntity<Map<String, Integer>> getProductsSoldLastMonth() {
		Map<String, Integer> productSalesMap = sellService.getProductsSoldInLastMonth();
		return new ResponseEntity<>(productSalesMap, HttpStatus.OK);
	}

}
