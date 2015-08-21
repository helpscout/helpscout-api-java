package net.helpscout.api.model.customer;

import java.util.Calendar;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    
	Long id;
	List<String> lines;
	String city;
	String state;
	String postalCode;
	String country;
	Calendar createdAt;
	Calendar modifiedAt;
}