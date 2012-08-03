package net.helpscout.api.model;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String timezone;
	private String photoUrl;
	private String createdAt;
	private String modifiedAt;
	
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
	public String getCreatedAt() {
		return createdAt;
	}
	public String getModifiedAt() {
		return modifiedAt;
	}
}