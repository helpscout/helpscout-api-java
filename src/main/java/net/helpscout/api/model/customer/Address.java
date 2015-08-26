package net.helpscout.api.model.customer;

import java.util.Calendar;
import java.util.List;

import lombok.Data;

@Data
public class Address {
    
	private Long id;
	private List<String> lines;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private Calendar createdAt;
	private Calendar modifiedAt;
}