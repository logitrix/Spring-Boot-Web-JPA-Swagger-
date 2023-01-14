package com.trustbridge.restaurant.dto;

import java.util.List;

import com.trustbridge.restaurant.bean.Order;
import com.trustbridge.restaurant.bean.Transaction;

public class TransactionResponse {

	private Transaction transaction;

	private List<Order> orders;

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
