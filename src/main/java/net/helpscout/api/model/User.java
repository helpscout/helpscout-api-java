package net.helpscout.api.model;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String timezone;
    private String photoUrl;
    private Date createdAt;
    private Date modifiedAt;
}