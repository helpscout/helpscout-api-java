package net.helpscout.api.model.customer;

public class Address extends Object {
	private int id;
	// private List<String> lines;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private String createdAt;
	private String modifiedAt;
	
	public int getId() {
		return id;
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
	public String getCreatedAt() {
		return createdAt;
	}
	public String getModifiedAt() {
		return modifiedAt;
	}
}