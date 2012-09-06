package net.helpscout.api.model.customer;

import java.util.Calendar;
import java.util.List;

public class Address {
	private int id;
	private List<String> lines;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private Calendar createdAt;
	private Calendar modifiedAt;
	
	public int getId() {
		return id;
	}
	public List<String> getLines() {
		return lines;
	}
	
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public String getCountry() {
		return country;
	}
	public Calendar getCreatedAt() {
		return createdAt;
	}
	public Calendar getModifiedAt() {
		return modifiedAt;
	}
}