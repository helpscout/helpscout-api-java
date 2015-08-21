package net.helpscout.api.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Source {
    
	String type;
	String via;
	
	public boolean isViaCustomer() {
		return "customer".equals(getVia());
	}
}