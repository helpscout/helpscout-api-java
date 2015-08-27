package net.helpscout.api.model.customer;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class SearchCustomer {

    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private List<String> emails;
    private String photoUrl;
    private String photoType;
    private String gender;
    private String age;
    private String organization;
    private String jobTitle;
    private String location;
    private Date createdAt;
    private Date modifiedAt;
}