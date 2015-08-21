package net.helpscout.api.model.customer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntry {
    
	Long id;
	String value;
	String type;
	String location;
}