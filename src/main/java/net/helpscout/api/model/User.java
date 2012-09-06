package net.helpscout.api.model;

import java.util.Calendar;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String timezone;
	private String photoUrl;
	private Calendar createdAt;
	private Calendar modifiedAt;
	
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getRole() {
		return role;
	}
	public String getTimezone() {
		return timezone;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public Calendar getCreatedAt() {
		return createdAt;
	}
	public Calendar getModifiedAt() {
		return modifiedAt;
	}
}