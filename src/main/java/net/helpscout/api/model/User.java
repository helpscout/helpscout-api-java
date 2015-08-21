package net.helpscout.api.model;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    
	Long id;
	String firstName;
	String lastName;
	String email;
	String role;
	String timezone;
	String photoUrl;
	Date createdAt;
	Date modifiedAt;
}