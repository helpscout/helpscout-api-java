package net.helpscout.api.model.customer;

import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchCustomer {

    Long id;
    String firstName;
    String lastName;
    String fullName;
    List<String> emails;
    String photoUrl;
    String photoType;
    String gender;
    String age;
    String organization;
    String jobTitle;
    String location;
    Date createdAt;
    Date modifiedAt;
}