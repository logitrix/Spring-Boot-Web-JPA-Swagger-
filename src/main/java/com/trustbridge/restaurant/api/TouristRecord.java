package com.trustbridge.restaurant.api;

import java.util.List;

public class TouristRecord {

	int page;
	int per_page;
	int totalrecord;
	int total_pages;
	List<Tourist> data;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPer_page() {
		return per_page;
	}

	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}

	public int getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}

	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}

	public List<Tourist> getData() {
		return data;
	}

	public void setData(List<Tourist> data) {
		this.data = data;
	}

}
