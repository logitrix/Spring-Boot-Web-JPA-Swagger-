package com.trustbridge.restaurant.dto;

import java.util.List;

import com.trustbridge.restaurant.bean.Order;

public class TransactionRequest {

	private String name;

	private String referenceNumber;

	private List<Order> orders;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

}
