package com.inventorymanagement.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inventorymanagement.entity.Sell;

public interface SellRepository extends JpaRepository<Sell, Long> {
	@Query("SELECT s " + "FROM Sell s " + "WHERE s.orderDate BETWEEN :startDate AND :endDate")
	List<Sell> findSellsInLastMonth(Date startDate, Date endDate);
}
