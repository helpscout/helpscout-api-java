package net.helpscout.api.model;

import java.util.Date;

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

	public Long getId() {
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", role=" + role + ", timezone=" + timezone + ", photoUrl=" + photoUrl + ", createdAt=" + createdAt
				+ ", modifiedAt=" + modifiedAt + "]";
	}

}