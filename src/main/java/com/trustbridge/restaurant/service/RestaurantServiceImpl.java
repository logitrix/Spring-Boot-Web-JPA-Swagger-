package com.trustbridge.restaurant.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.trustbridge.restaurant.bean.Order;
import com.trustbridge.restaurant.bean.Product;
import com.trustbridge.restaurant.bean.Transaction;
import com.trustbridge.restaurant.dao.OrderRepository;
import com.trustbridge.restaurant.dao.ProductRepository;
import com.trustbridge.restaurant.dao.TransactionRepository;
import com.trustbridge.restaurant.dto.TransactionRequest;
import com.trustbridge.restaurant.dto.TransactionResponse;
import com.trustbridge.restaurant.util.Constant;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}
	
	@Override
	public TransactionResponse viewOrder(String referenceNumber) throws Exception {
		if (referenceNumber == null || referenceNumber.trim().isEmpty()) {
			throw new Exception("Please add reference number!");
		}
		
		Transaction txn = transactionRepository.findByReferenceNumber(referenceNumber);
		if (txn == null) {
			throw new Exception("Reference Number doesn't exist!");
		}
		if (Constant.STATUS_CANCEL.equals(txn.getStatus())) {
			throw new Exception("Already cancel!");
		}

		List<Order> orders = orderRepository.findByReferenceNumber(referenceNumber);
		for (int i = 0; i < orders.size() ; i++) {
			Product product = (Product) productRepository.findById(orders.get(i).getProductId()).orElse(null);;
			if(product != null) {
				orders.get(i).setProductName(product.getName());
				orders.get(i).setDescription(product.getDescription());
			} else {
				throw new Exception("Product ID " + orders.get(i).getProductId() +" doesn't exist!");
			}
		}
		
		TransactionResponse response = new TransactionResponse();
		response.setTransaction(txn);
		response.setOrders(orders);
		return response;
	}

	@Override
	public TransactionResponse sendOrder(TransactionRequest request) throws Exception {
		
		Transaction txn = new Transaction();
		txn.setOrderedBy(request.getName());
		txn.setReferenceNumber(generateDateRefNumber());
		txn.setStatus(Constant.STATUS_ACCEPTED);
		txn.setDate(Date.valueOf(LocalDate.now()));
		
		List<Order> orders = new ArrayList<>();
		int id = 1;
		for (Order order : request.getOrders()) {
			Product product = (Product) productRepository.findById(order.getId()).orElse(null);;
			if(product != null) {
				Order validOrder = new Order();
				validOrder.setId(id++);
				validOrder.setProductId(product.getId());
				validOrder.setProductName(product.getName());
				validOrder.setDescription(product.getDescription());
				validOrder.setReferenceNumber(txn.getReferenceNumber());
				validOrder.setDateOrdered(Date.valueOf(LocalDate.now()));
				orders.add(validOrder);
			} else {
				throw new Exception("Product ID " + order.getId() +" doesn't exist!");
			}
		}
		
		transactionRepository.save(txn);
		orderRepository.saveAll(orders);

		TransactionResponse response = new TransactionResponse();
		response.setTransaction(txn);
		response.setOrders(orders);
		return response;
	}
	
	@Override
	public TransactionResponse updateOrder(TransactionRequest request) throws Exception {
		
		if (request.getReferenceNumber() == null || request.getReferenceNumber().trim().isEmpty()) {
			throw new Exception("Please add reference number!");
		}
		
		Transaction txn = transactionRepository.findByReferenceNumber(request.getReferenceNumber());
		if (txn == null) {
			throw new Exception("Reference Number doesn't exist!");
		}
		if (Constant.STATUS_CANCEL.equals(txn.getStatus())) {
			throw new Exception("Already cancel!");
		}

		txn.setStatus(Constant.STATUS_MODIFIED);
		txn.setDate(Date.valueOf(LocalDate.now()));
		List<Order> newOrders = new ArrayList<>();
		int id = 1;
		for (Order order : request.getOrders()) {
			Product product = (Product) productRepository.findById(order.getId()).orElse(null);;
			if(product != null) {
				Order validOrder = new Order();
				validOrder.setId(id++);
				validOrder.setProductId(product.getId());
				validOrder.setProductName(product.getName());
				validOrder.setDescription(product.getDescription());
				validOrder.setReferenceNumber(txn.getReferenceNumber());
				validOrder.setDateOrdered(Date.valueOf(LocalDate.now()));
				newOrders.add(validOrder);
			} else {
				throw new Exception("Product ID " + order.getId() +" doesn't exist!");
			}
		}
		
		List<Order> orders = orderRepository.findByReferenceNumber(request.getReferenceNumber());
		
		transactionRepository.save(txn);
		orderRepository.deleteAll(orders);
		orderRepository.saveAll(newOrders);
		
		TransactionResponse response = new TransactionResponse();
		response.setTransaction(txn);
		response.setOrders(newOrders);
		return response;
	}
	
	@Override
	public TransactionResponse cancelOrder(String referenceNumber) throws Exception {
		
		if (referenceNumber == null || referenceNumber.trim().isEmpty()) {
			throw new Exception("Please add reference number!");
		}
		
		Transaction txn = transactionRepository.findByReferenceNumber(referenceNumber);
		if (txn == null) {
			throw new Exception("Reference Number doesn't exist!");
		}
		if (Constant.STATUS_CANCEL.equals(txn.getStatus())) {
			throw new Exception("Already cancel!");
		}
		
		List<Order> orders = orderRepository.findByReferenceNumber(referenceNumber);
		
		txn.setStatus(Constant.STATUS_CANCEL);
		txn.setDate(Date.valueOf(LocalDate.now()));
		transactionRepository.save(txn);
		orderRepository.deleteAll(orders);
		
		TransactionResponse response = new TransactionResponse();
		response.setTransaction(txn);
		response.setOrders(orders);
		return response;
	}

	private String generateDateRefNumber() {
		LocalDateTime date = LocalDateTime.now();
		return date.toString().replaceAll("[^a-zA-Z0-9]","");
	}

}
