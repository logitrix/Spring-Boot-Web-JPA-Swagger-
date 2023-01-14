package com.trustbridge.restaurant.service;

import java.util.List;

import com.trustbridge.restaurant.bean.Product;
import com.trustbridge.restaurant.dto.TransactionRequest;
import com.trustbridge.restaurant.dto.TransactionResponse;

public interface RestaurantService {

	List<Product> getProducts();
	
	TransactionResponse sendOrder(TransactionRequest request) throws Exception;
	
	TransactionResponse updateOrder(TransactionRequest request) throws Exception;

	TransactionResponse cancelOrder(String referenceNumber) throws Exception;

	TransactionResponse viewOrder(String referenceNumber) throws Exception;

}
