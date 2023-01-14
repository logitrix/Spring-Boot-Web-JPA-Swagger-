package com.trustbridge.restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trustbridge.restaurant.bean.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	Transaction findByReferenceNumber(String referenceNumber);

}
