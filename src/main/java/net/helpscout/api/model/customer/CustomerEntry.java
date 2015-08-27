package net.helpscout.api.model.customer;

import lombok.Data;

@Data
public class CustomerEntry {
    
    private Long id;
    private String value;
    private String type;
    private String location;
}