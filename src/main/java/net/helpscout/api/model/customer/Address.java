package net.helpscout.api.model.customer;

import java.util.Date;
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
    private Date createdAt;
    private Date modifiedAt;
}