package com.trustbridge.restaurant.bean;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	private long id;
	
	@Column(name = "reference_number")
	private String referenceNumber;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "date_ordered")
	private Date dateOrdered;
	
	@Transient
	private String productName;
	
	@Transient
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
