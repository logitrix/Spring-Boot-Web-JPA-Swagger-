package com.trustbridge.restaurant.api;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Tourist {

	int id;
	String tourist_name;
	String tourist_email;
	String tourist_location;
	Date createdat;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTourist_name() {
		return tourist_name;
	}

	public void setTourist_name(String tourist_name) {
		this.tourist_name = tourist_name;
	}

	public String getTourist_email() {
		return tourist_email;
	}

	public void setTourist_email(String tourist_email) {
		this.tourist_email = tourist_email;
	}

	public String getTourist_location() {
		return tourist_location;
	}

	public void setTourist_location(String tourist_location) {
		this.tourist_location = tourist_location;
	}

	public Date getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}
	
	public String toJson() {
		return "{ " +
			  "tourist_name : " + getTourist_name() + ", " +
			  "tourist_email : " + getTourist_email() + ", " + 
			  "tourist_location : " + getTourist_location() + 
			" }";
	}

}
