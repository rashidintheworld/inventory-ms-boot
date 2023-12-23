package com.inventorymanagement.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventorymanagement.dto.SellDTO;
import com.inventorymanagement.entity.Product;
import com.inventorymanagement.entity.Sell;
import com.inventorymanagement.exception.NotFoundException;
import com.inventorymanagement.repo.ProductRepository;
import com.inventorymanagement.repo.SellRepository;
import com.inventorymanagement.service.SellService;

@Service
public class SellServiceImpl implements SellService {

	@Autowired
	private SellRepository sellRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<SellDTO> getAllSells() {
		List<Sell> sells = sellRepository.findAll();
		return sells.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public SellDTO getSellById(Long id) {
		Optional<Sell> optionalSell = sellRepository.findById(id);
		if (optionalSell.isPresent()) {
			return convertToDTO(optionalSell.get());
		} else {
			throw new NotFoundException("Sell not found");
		}
	}

	@Override
	@Transactional
	public SellDTO createSell(SellDTO sellDTO) {
		Sell sell = convertToEntity(sellDTO);
		sell = sellRepository.save(sell);
		return convertToDTO(sell);
	}

	private SellDTO convertToDTO(Sell sell) {
		SellDTO sellDTO = new SellDTO();
		sellDTO.setId(sell.getId());
		sellDTO.setProductId(sell.getProductId());
		sellDTO.setQuantity(sell.getQuantity());
		sellDTO.setOrderDate(sell.getOrderDate());
		// Set other fields as needed
		return sellDTO;
	}

	private Sell convertToEntity(SellDTO sellDTO) {
		Sell sell = new Sell();
		sell.setProductId(sellDTO.getProductId());
		sell.setQuantity(sellDTO.getQuantity());
		sell.setOrderDate(sellDTO.getOrderDate());
		return sell;
	}

	@Override
	public Map<String, Integer> getProductsSoldInLastMonth() {
		LocalDate last30DaysStart = LocalDate.now().minusDays(30);
	    LocalDate today = LocalDate.now();
	    Date last30DaysStartDate = Date.from(last30DaysStart.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    Date todayEndDate = Date.from(today.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());
		List<Sell> sellsInLastMonth = sellRepository.findSellsInLastMonth(last30DaysStartDate, todayEndDate);
		Map<String, Integer> productSalesMap = new HashMap<>();
		for (Sell sell : sellsInLastMonth) {
			Long productId = sell.getProductId();
			Product product = productRepository.findById(productId)
					.orElseThrow(() -> new NotFoundException("Product not found for ID: " + productId));
			String productName = product.getName();
			int quantitySold = sell.getQuantity();
			productSalesMap.merge(productName, quantitySold, Integer::sum);
		}
		return productSalesMap;
	}

}
